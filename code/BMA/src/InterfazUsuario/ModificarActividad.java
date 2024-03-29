/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazUsuario;

import InterfazUsuario.PantallaPrincipal;
import GestionActividades.*;
import ServiciosAlmacenamiento.BaseDatos;
import java.awt.Color;
import java.sql.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

/**
 *
 * @author jesusmcs
 */
public class ModificarActividad extends javax.swing.JFrame {

    BaseDatos accesoBD;
    int idActividad;
    PantallaPrincipal Pprincipal;

    /**
     * Creates new form ModificarActividad
     */
    public ModificarActividad() {
        initComponents();
    }

    public ModificarActividad(BaseDatos acceso, String nombre, String fechaInicio, String fechaFin, int id, String descp, int plazas, PantallaPrincipal p) {
        accesoBD = acceso;
        initComponents();
        nombreTextField.setText(nombre);
        fechaInicioInicial.setText(fechaInicio);
        FechaFinInicial.setText(fechaFin);
        jTextArea1.append(descp);
        plazasTextField.setText(Integer.toString(plazas));
        idActividad = id;
        instalacion.removeAllItems();
        jComboBox1.removeAllItems();
        Pprincipal = p;

        ResultSet retset;
        String consulta = "SELECT nombre FROM instalacion";
        retset = accesoBD.ejecutaConsulta(consulta);
        try {
            while (retset.next()) {
                System.out.print("\n" + retset.getString(1));
                instalacion.addItem(retset.getString(1).toString());

            }
        } catch (SQLException ex) {
            Logger.getLogger(AltaActividad.class.getName()).log(Level.SEVERE, null, ex);
        }

        ResultSet rts;
        String consultaCurso = "SELECT curso FROM temporada";
        rts = accesoBD.ejecutaConsulta(consultaCurso);
        try {
            while (rts.next()) {
                jComboBox1.addItem(rts.getString(1).toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(AltaActividad.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        modificarLabel = new javax.swing.JLabel();
        nombreLabel = new javax.swing.JLabel();
        nombreTextField = new javax.swing.JTextField();
        fechaInicioLabel = new javax.swing.JLabel();
        fechaInicioDateChooser = new com.toedter.calendar.JDateChooser();
        fechaFinLabel = new javax.swing.JLabel();
        fechaFinDateChooser = new com.toedter.calendar.JDateChooser();
        descripcionLabel = new javax.swing.JLabel();
        descripcionScrollPane = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        Guardar = new javax.swing.JButton();
        Cancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        plazasTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        fechaInicioInicial = new javax.swing.JLabel();
        FechaFinInicial = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        instalacion = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        modificarLabel.setText("Modificar Actividad");

        nombreLabel.setText("Nombre");

        fechaInicioLabel.setText("Fecha Inicio");

        fechaFinLabel.setText("Fecha fin");

        descripcionLabel.setText("Descripcion");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        descripcionScrollPane.setViewportView(jTextArea1);

        Guardar.setText("Guardar");
        Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });

        Cancelar.setText("Cancelar");
        Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarActionPerformed(evt);
            }
        });

        jLabel1.setText("Plazas");

        jLabel2.setText("Temporada");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2011/2012", "2012/2013", "2013/2014" }));

        fechaInicioInicial.setText("Label3");

        FechaFinInicial.setText("jLabel3");

        jLabel3.setText("Instalacion");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(modificarLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nombreLabel)
                        .addGap(18, 18, 18)
                        .addComponent(nombreTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fechaInicioLabel)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(descripcionLabel)
                        .addGap(20, 20, 20)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(fechaInicioInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(121, 121, 121)
                        .addComponent(FechaFinInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(fechaInicioDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(fechaFinLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(plazasTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fechaFinDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBox1, 0, 90, Short.MAX_VALUE)
                                    .addComponent(instalacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(Guardar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(Cancelar))
                        .addComponent(descripcionScrollPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(modificarLabel)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nombreLabel)
                            .addComponent(nombreTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(fechaInicioInicial)
                                    .addComponent(FechaFinInicial))
                                .addGap(11, 11, 11)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fechaInicioLabel)
                                    .addComponent(fechaFinDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(fechaFinLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(fechaInicioDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(instalacion, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(plazasTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(descripcionScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cancelar)
                    .addComponent(Guardar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarActionPerformed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_CancelarActionPerformed

    private int getIDTemporada() {
        ResultSet retset;
        String consulta = ("SELECT idTemporada FROM temporada WHERE curso = '" + jComboBox1.getSelectedItem().toString()
                + "'");
        int temporada = 0;

        retset = accesoBD.ejecutaConsulta(consulta);
        try {
            if (retset.next()) {
                temporada = retset.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModificarActividad.class.getName()).log(Level.SEVERE, null, ex);
        }

        return temporada;

    }
    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarActionPerformed
        // TODO add your handling code here:
        boolean exito;
        String errores = "";
        int idTemporada = getIDTemporada();
        String FechaInicio = (Integer.toString(fechaInicioDateChooser.getCalendar().get(java.util.Calendar.YEAR)) + "-"
                + Integer.toString(fechaInicioDateChooser.getCalendar().get(java.util.Calendar.MONTH)) + "-"
                + Integer.toString(fechaInicioDateChooser.getCalendar().get(java.util.Calendar.DATE)));

        System.out.print(FechaInicio);

        String FechaFin = (Integer.toString(fechaFinDateChooser.getCalendar().get(java.util.Calendar.YEAR)) + "-"
                + Integer.toString(fechaFinDateChooser.getCalendar().get(java.util.Calendar.MONTH)) + "-"
                + Integer.toString(fechaFinDateChooser.getCalendar().get(java.util.Calendar.DATE)));


        if (fechaInicioDateChooser == null || fechaFinDateChooser == null) {
            JOptionPane.showMessageDialog(null, "No has seleccionado una fecha",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

        exito = GestorActividad.modificaActividad(accesoBD, idActividad, jTextArea1.getText(), plazasTextField.getText(),
                "50", "70", Integer.toString(idTemporada), FechaInicio, FechaFin, nombreTextField.getText());

        if (!exito) {
            JOptionPane.showMessageDialog(null, "Ha habido un error en la base de datos",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } else if (fechaInicioDateChooser.getDate() == null || fechaFinDateChooser.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Nuevas fechas no introducidas",
                    "Aviso", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Actividad modificada con exito",
                    "Confirmacion", JOptionPane.INFORMATION_MESSAGE);

            //InterfazUsuario.PantallaPrincipal.ActualizarTabla();
            this.setVisible(false);
            //this.setEnabled(false);
            this.dispose();
        }

        Pprincipal.mostrarActividades();
    }//GEN-LAST:event_GuardarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ModificarActividad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModificarActividad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModificarActividad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModificarActividad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModificarActividad().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cancelar;
    private javax.swing.JLabel FechaFinInicial;
    private javax.swing.JButton Guardar;
    private javax.swing.JLabel descripcionLabel;
    private javax.swing.JScrollPane descripcionScrollPane;
    private com.toedter.calendar.JDateChooser fechaFinDateChooser;
    private javax.swing.JLabel fechaFinLabel;
    private com.toedter.calendar.JDateChooser fechaInicioDateChooser;
    private javax.swing.JLabel fechaInicioInicial;
    private javax.swing.JLabel fechaInicioLabel;
    private javax.swing.JComboBox instalacion;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel modificarLabel;
    private javax.swing.JLabel nombreLabel;
    private javax.swing.JTextField nombreTextField;
    private javax.swing.JTextField plazasTextField;
    // End of variables declaration//GEN-END:variables
}
