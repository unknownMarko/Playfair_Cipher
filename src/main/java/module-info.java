module com.playfaircipher.playfair_cipher {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.playfaircipher.playfair_cipher to javafx.fxml;
    exports com.playfaircipher.playfair_cipher;
}