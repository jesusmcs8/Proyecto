/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazUsuario;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class NuevaTemporada extends javax.swing.JFrame {
    
    private PantallaPrincipal creador;

    /**
     * Creates new form NuevaTemporada
     */
    public NuevaTemporada() {
        initComponents();
        this.setLocation(300, 300);
    }
    
    public NuevaTemporada(PantallaPrincipal v){
        initComponents();
        creador = v;
        this.setLocation(300, 300);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        botonAceptar = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        labelCurso = new javax.swing.JLabel();
        chooserCurso = new com.toedter.calendar.JYearChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nueva Temporada");
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        botonAceptar.setText("Aceptar");
        botonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(14, 0, 14, 15);
        getContentPane().add(botonAceptar, gridBagConstraints);

        botonCancelar.setText("Cancelar");
        botonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 44);
        getContentPane().add(botonCancelar, gridBagConstraints);

        jLabel1.setText("Seleccionar curso:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 20, 0);
        getContentPane().add(jLabel1, gridBagConstraints);

        jLabel2.setText("Se creará la temporada:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(14, 16, 14, 16);
        getContentPane().add(jLabel2, gridBagConstraints);

        labelCurso.setText("2013/2014");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(labelCurso, gridBagConstraints);

        chooserCurso.setValue(2012);
        chooserCurso.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                chooserCursoPropertyChange(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(chooserCurso, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chooserCursoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_chooserCursoPropertyChange
        int aux1 = chooserCurso.getYear();
        int aux2 = chooserCurso.getYear()+1;
        String auxCurso = Integer.toString(aux1)+"/"+Integer.toString(aux2);
        
        labelCurso.setText(auxCurso);
    }//GEN-LAST:event_chooserCursoPropertyChange

    private void botonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarActionPerformed
        int aux1 = chooserCurso.getYear();
        int aux2 = chooserCurso.getYear()+1;
        String auxCurso = Integer.toString(aux1)+"/"+Integer.toString(aux2);
       
        String query = "SELECT * FROM Temporada WHERE curso='"+auxCurso+"'";
        ResultSet res = creador.ejecutarConsulta(query);
        
        try {
            if(res.next()){
                JOptionPane.showMessageDialog(this, "La temporada ya existe", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{

                int conf = JOptionPane.showConfirmDialog(this, "¿Desea crear la temporada?");
                if(conf == JOptionPane.YES_OPTION){
                    JOptionPane.showMessageDialog(this, "Temporada creada", "Exito", JOptionPane.NO_OPTION);
                    query = "INSERT INTO Temporada (curso) VALUES ('"+auxCurso+"')";
                    creador.ejecutarActualizacion(query);
                    this.setVisible(false);
                }   
            }
        } catch (SQLException ex) {
            Logger.getLogger(NuevaTemporada.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            creador.actualizaComboBoxTemporadas();
        } catch (SQLException ex) {
            Logger.getLogger(NuevaTemporada.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botonAceptarActionPerformed

    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_botonCancelarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NuevaTemporada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NuevaTemporada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NuevaTemporada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NuevaTemporada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new NuevaTemporada().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAceptar;
    private javax.swing.JButton botonCancelar;
    private com.toedter.calendar.JYearChooser chooserCurso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel labelCurso;
    // End of variables declaration//GEN-END:variables
}
