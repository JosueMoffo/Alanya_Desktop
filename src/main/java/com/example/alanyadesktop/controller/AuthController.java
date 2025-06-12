package com.example.alanyadesktop.controller;

import com.example.alanyadesktop.MainApp;
import com.example.alanyadesktop.model.AuthRequest;
import com.example.alanyadesktop.model.AuthResponse;
import com.example.alanyadesktop.service.AuthService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.ResourceBundle;

public class AuthController implements Initializable {


    //@Setter
    private MainApp mainApp;
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    // Champs du formulaire FXML
    @FXML private TextField userNameField;
    @FXML private TextField userPhoneField;
    @FXML private PasswordField passField;
    @FXML private PasswordField confirmPassField;
    @FXML private Label confirmLabel;
    @FXML private Label nameLabel;
    @FXML private Button signUp;
    @FXML private Button login;
    @FXML private Label messageLogin;

    private final AuthService authService = new AuthService();

    // Numéro de téléphone de l'utilisateur (pour le partager avec d'autres contrôleurs)
    @Getter
    private static String userPhone;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("AUTH CONTROLOR");
    }


    @FXML
    public void onLogin() {
        userPhone = userPhoneField.getText().trim();
        String password = passField.getText().trim();

        // Validation des champs
        if (userPhone.isEmpty() || password.isEmpty()) {
            showError("Veuillez renseigner le numéro de téléphone et le mot de passe");
            return;
        }

        try {
            // Création de la requête d'authentification
            AuthRequest request = new AuthRequest(userPhone, password);

            // Appel au service d'authentification
            AuthResponse response = authService.login(request);

            MainApp.setUserId(response.getUserId());
            MainApp.setAuthToken(response.getAccessToken());

            mainApp.showMainView();

        } catch (Exception e) {
            showError("Échec de la connexion: " + e.getMessage());
        }
    }


    @FXML
    public void onSignUp() {
        String userName = userNameField.getText().trim();
        userPhone = userPhoneField.getText().trim();
        String password = passField.getText().trim();
        String confirmPassword = confirmPassField.getText().trim();

        // Si le champ de confirmation n'est pas visible, on l'affiche
        if (!confirmPassField.isVisible()) {
            nameLabel.setVisible(true);
            userNameField.setVisible(true);
            confirmLabel.setVisible(true);
            confirmPassField.setVisible(true);
            login.setVisible(false);
            return;
        }

        // Validation des champs
        if (userName.isEmpty() || userPhone.isEmpty() || password.isEmpty()) {
            showError("Veuillez remplir tous les champs");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showError("Les mots de passe ne correspondent pas");
            return;
        }

        try {
            AuthRequest request = new AuthRequest(userPhone, password, userName);

            AuthResponse response = authService.register(request);

            MainApp.setUserId(response.getUserId());
            MainApp.setAuthToken(response.getAccessToken());

            showError("Inscription réussie! ");
            mainApp.showMainView();

        } catch (Exception e) {
            showError("Échec de l'inscription: " + e.getMessage());
        }
    }

    /**
     * Affiche un message d'erreur
     */
    private void showError(String message) {
        messageLogin.setText(message);
        messageLogin.setVisible(true);
        System.err.println("[ERREUR] " + message);
    }

    /**
     * Réinitialise le formulaire
     */
    private void resetForm() {
        nameLabel.setVisible(false);
        userNameField.setVisible(false);
        confirmLabel.setVisible(false);
        confirmPassField.setVisible(false);
        login.setVisible(true);
        userNameField.clear();
        confirmPassField.clear();
        passField.clear();
    }
}