import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Font;  // Para ajustar el tamaño de la fuente
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

public class verificarTaTeTi extends JFrame {

    private int ronda = 0;
    private boolean jugadorEquis = true;
    private JButton[][] tablero = new JButton[3][3];

    // Constructor de la clase
    public verificarTaTeTi() {
        // Título, tamaño y layout manager
        this.setTitle("Ta-Te-Ti");
        this.setSize(690, 690);
        this.setLayout(new GridLayout(3, 3));
        //Aca iria para agregarle un icono, por que son las "settings" de la ventana
        

        // Creación de los 9 botones y añadido al tablero
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = new JButton();
                tablero[i][j].setFont(new Font("Arial", Font.BOLD, 80));  // Ajusta el tamaño de la fuente
                tablero[i][j].addActionListener(e -> jugar(e));
                this.add(tablero[i][j]);
            }
        }

        // Cierre y visibilidad
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Método que maneja el clic en los botones
    private void jugar(ActionEvent e) {

        // Obtenemos el botón que se ha pulsado
        JButton botonPulsado = (JButton) e.getSource();

        // Si juega la equis
        if (jugadorEquis) {
            botonPulsado.setBackground(Color.GREEN);   // Cambia el color
            botonPulsado.setText("X");                 // Agrega la X
            botonPulsado.setEnabled(false);            // Desactiva el botón
            jugadorEquis = false;
        }
        // Si juega la O
        else {
            botonPulsado.setBackground(Color.YELLOW);  // Cambia el color
            botonPulsado.setText("O");                 // Agrega el círculo O
            botonPulsado.setEnabled(false);            // Desactiva el botón
            jugadorEquis = true;
        }
        ronda++;
        verificarFinDelJuego();
    }

    // Verifica si tres botones tienen el mismo texto (y por lo tanto, si hay una victoria)
    private boolean verificarTaTeTi(JButton p1, JButton p2, JButton p3) {
        return p1.getText().equals(p2.getText()) && p2.getText().equals(p3.getText())
                && !p1.getText().equals("");
    }

    // Reinicia el tablero y las variables del juego
    private void reiniciarJuego() {
        // Reiniciar botones
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; ++j) {
                tablero[i][j].setEnabled(true);
                tablero[i][j].setBackground(null);
                tablero[i][j].setText("");
            }
        }
        // Reiniciar juego
        jugadorEquis = true;
        ronda = 0;
    }

    // Muestra el mensaje de victoria y reinicia el juego
    private void victoria() {
        JOptionPane.showMessageDialog(this, "El jugador " + (jugadorEquis ? "O" : "X") + " gana");
        reiniciarJuego();
    }

    // Muestra el mensaje de empate y reinicia el juego
    private void empate() {
        JOptionPane.showMessageDialog(null, "Empate");
        reiniciarJuego();
    }

    // Verifica si el juego ha terminado (victoria o empate)
    private void verificarFinDelJuego() {
        // Victoria en filas o columnas
        for (int i = 0; i < 3; i++) {
            if (verificarTaTeTi(tablero[i][0], tablero[i][1], tablero[i][2])
                    || verificarTaTeTi(tablero[0][i], tablero[1][i], tablero[2][i])) {
                victoria();
                return;
            }
        }

        // Victoria en diagonales
        if (verificarTaTeTi(tablero[0][0], tablero[1][1], tablero[2][2])
                || verificarTaTeTi(tablero[0][2], tablero[1][1], tablero[2][0])) {
            victoria();
            return;
        }

        // Empate
        if (ronda == 9) {
            empate();
        }
    }

    // Método main para ejecutar el juego
    public static void main(String[] args) {
        new verificarTaTeTi();
    }
}
