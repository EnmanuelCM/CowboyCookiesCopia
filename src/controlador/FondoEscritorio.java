
package controlador;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;

/**
 *
 * @author enmac
 */
public class FondoEscritorio extends JDesktopPane {
    
     private Image imagen;

    public FondoEscritorio() {
        // Asegúrate de que la ruta esté bien: dentro de src/main/resources o similar
        imagen = new ImageIcon(getClass().getResource("/backgrounds/Fondo_MenuPrincipal.jpg")).getImage();
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagen != null) {
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
        }
    }
    
    
}
