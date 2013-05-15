/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazUsuario;

import GestionDeAlumnos.*;
import GestionDePagos.*;
import ServiciosAlmacenamiento.BaseDatos;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

/**
 *
 * @author USER
 */
public class AñadirAlumno extends javax.swing.JFrame {

    int idTemporada, idActividad;
    BaseDatos accesoBD;
    List<String> listaAlumnos = new ArrayList<String>();
    List<String> listaAlumnosQuitados = new ArrayList<String>();
    List<Integer> listaIDAlumnos = new ArrayList<Integer>();
    List<Integer> listaIDAlumnosQuitados = new ArrayList<Integer>();

    /**
     * Creates new form AñadirAlumno
     */
    public AñadirAlumno() {
        initComponents();
    }

    public AñadirAlumno(BaseDatos acceso, int idTemp, int idAct) {
        accesoBD = acceso;
        initComponents();
        idTemporada = idTemp;
        idActividad = idAct;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Titulo = new javax.swing.JLabel();
        Buscar = new javax.swing.JLabel();
        Nombre = new javax.swing.JLabel();
        NombreTextField = new javax.swing.JTextField();
        PrimerApellido = new javax.swing.JLabel();
        PrimerApellidoTextField = new javax.swing.JTextField();
        SegundoApellido = new javax.swing.JLabel();
        SegundoApellidoTextField = new javax.swing.JTextField();
        Añadir = new javax.swing.JButton();
        Guardar = new javax.swing.JButton();
        Salir = new javax.swing.JButton();
        Mostrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        AlumnosSelecionados = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ListaAlumnos = new javax.swing.JList();
        jScrollPane1 = new javax.swing.JScrollPane();
        AlumnosMostrados = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Quitar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        Titulo.setText("Añadir alumno");

        Buscar.setText("Buscar por");

        Nombre.setText("Nombre");

        PrimerApellido.setText("Primer Apellido");

        SegundoApellido.setText("Segudno Apellido");

        Añadir.setText("Añadir");
        Añadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AñadirActionPerformed(evt);
            }
        });

        Guardar.setText("Guardar");
        Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });

        Salir.setText("Salir");
        Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirActionPerformed(evt);
            }
        });

        Mostrar.setText("Mostrar");
        Mostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MostrarActionPerformed(evt);
            }
        });

        jLabel1.setText("Alumnos Seleccionados");

        AlumnosSelecionados.setText("0");

        jScrollPane2.setViewportView(ListaAlumnos);

        jScrollPane1.setViewportView(AlumnosMostrados);

        jLabel2.setText("Alumnos Apuntados:");

        jLabel3.setText("0");

        Quitar.setText("Quitar");
        Quitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QuitarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PrimerApellido)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Mostrar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Quitar)
                                .addGap(4, 4, 4)
                                .addComponent(Añadir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Guardar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Salir))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(PrimerApellidoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(SegundoApellido)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SegundoApellidoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Buscar)
                            .addComponent(Titulo)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Nombre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(NombreTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(AlumnosSelecionados)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)))
                .addGap(41, 41, 41))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(Titulo)
                .addGap(29, 29, 29)
                .addComponent(Buscar)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Nombre)
                    .addComponent(NombreTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PrimerApellido)
                    .addComponent(PrimerApellidoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SegundoApellido)
                    .addComponent(SegundoApellidoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(AlumnosSelecionados)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Salir)
                    .addComponent(Guardar)
                    .addComponent(Añadir)
                    .addComponent(Mostrar)
                    .addComponent(Quitar))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AñadirActionPerformed
        // TODO add your handling code here:
        listaAlumnos.addAll(ListaAlumnos.getSelectedValuesList());
        System.out.print(listaAlumnos);
        AlumnosSelecionados.setText(Integer.toString(listaAlumnos.size()));
    }//GEN-LAST:event_AñadirActionPerformed

    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_SalirActionPerformed

    private String leerConsulta() {
        String consulta = new String("SELECT nombre, primerApellido, segundoApellido from alumno ");
        String condicion = new String("WHERE ");

        if (!NombreTextField.getText().equals("") || !PrimerApellidoTextField.getText().equals("")
                || !SegundoApellidoTextField.getText().equals("")) {
            if (!NombreTextField.getText().equals("")) {
                condicion = condicion + " nombre = '" + NombreTextField.getText() + "' AND";
            }
            if (!PrimerApellidoTextField.getText().equals("")) {
                condicion = condicion + " primerApellido = '" + PrimerApellidoTextField.getText() + "' AND";
            }
            if (!SegundoApellidoTextField.getText().equals("")) {
                condicion = condicion + " segundoApellido = '" + SegundoApellidoTextField.getText() + "' AND";
            }

            //condicion = condicion.substring(0, condicion.length() - 3);




        }

        condicion = condicion + " idAlumno NOT IN (select Alumno_idAlumno from pagoactividades WHERE Actividades_idActividades = "
                + idActividad + ")";
        consulta = consulta + condicion;
        System.out.print("\n\n Consulta todos alumnos" + consulta);
        return consulta;
    }

    private void alumnosSelecionados() {

        String consulta = ("SELECT nombre, primerApellido, segundoApellido from alumno WHERE idAlumno IN "
                + "(select Alumno_idAlumno from pagoactividades where Actividades_idActividades =" + idActividad + ")");

        ResultSet retset;
        int nAl = 0;

        retset = accesoBD.ejecutaConsulta(consulta);

        DefaultListModel modelo = new DefaultListModel();
        try {
            while (retset.next()) {
                modelo.addElement(retset.getString("primerApellido")
                        + " " + retset.getString("segundoApellido") + " " + retset.getString("nombre"));
                nAl++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AñadirAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        AlumnosMostrados.setModel(modelo);
        jLabel3.setText(Integer.toString(nAl));

    }

    private void MostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MostrarActionPerformed
        try {
            // TODO add your handling code here:
            String mostrar_alumnos = leerConsulta();
            ResultSet retset;
            retset = accesoBD.ejecutaConsulta(mostrar_alumnos);
            /*
             AlumnosTable.setModel(new javax.swing.table.DefaultTableModel(
             new Object[][]{
             {null, null, null},
             {null, null, null},
             {null, null, null},
             {null, null, null},
             {null, null, null},
             {null, null, null},
             {null, null, null},
             {null, null, null},
             {null, null, null},
             {null, null, null},
             {null, null, null},
             {null, null, null},
             {null, null, null},
             {null, null, null},
             {null, null, null},
             {null, null, null},
             {null, null, null},
             {null, null, null},
             {null, null, null},
             {null, null, null},
             {null, null, null},
             {null, null, null},
             {null, null, null},
             {null, null, null},
             {null, null, null}
             },
             new String[]{
             "Nombre", "Primer Apellido", "Segundo Apellido"
             }));
             javax.swing.table.TableModel modelo_tabla = new javax.swing.table.DefaultTableModel();
             modelo_tabla = AlumnosTable.getModel();
             int i = 0;
             while (retset.next()) {

             if (i < 25) {
             AlumnosTable.setValueAt(retset.getString("nombre"), i, 0);
             AlumnosTable.setValueAt(retset.getString("primerApellido"), i, 1);
             AlumnosTable.setValueAt(retset.getString("segundoApellido"), i, 2);
             } else {
             javax.swing.table.DefaultTableModel temp = (javax.swing.table.DefaultTableModel) AlumnosTable.getModel();
             Object nuevo[] = {"", "", ""};
             temp.addRow(nuevo);
             AlumnosTable.setValueAt(retset.getString("nombre"), i, 0);
             AlumnosTable.setValueAt(retset.getString("primerApellido"), i, 1);
             AlumnosTable.setValueAt(retset.getString("segundoApellido"), i, 2);
             }
             i++;
             }
             */

            DefaultListModel modelo = new DefaultListModel();
            while (retset.next()) {
                modelo.addElement(retset.getString("primerApellido")
                        + " " + retset.getString("segundoApellido") + " " + retset.getString("nombre"));
            }
            ListaAlumnos.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(AñadirAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }

        alumnosSelecionados();

    }//GEN-LAST:event_MostrarActionPerformed

    public int getIDCuota() throws SQLException {
        String Consulta = "SELECT MAX(idCuota) FROM Cuota";
        int id = 0;
        ResultSet retset = accesoBD.ejecutaConsulta(Consulta);
        if (retset.next()) {
            id = retset.getInt(1);
        }
        return id;
    }

    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarActionPerformed
        System.out.print("\n Antes del try" + listaAlumnos);
        try {
            System.out.print("\n En el try" + listaAlumnos);
            listaIDAlumnos = GestorAlumnos.getIdAl(accesoBD, listaAlumnos);
        } catch (SQLException ex) {
            Logger.getLogger(AñadirAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (listaIDAlumnos.size() > 0) {
            ResultSet retset, rts;
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Calendar cal1 = Calendar.getInstance();
            for (int i = 0; i < listaIDAlumnos.size(); i++) {
                String insert = new String("INSERT INTO cuota (importe, fecha, pagado) VALUES (50, \""
                        + cal1.get(Calendar.YEAR) + "-" + cal1.get(Calendar.MONTH) + "-" + cal1.get(Calendar.DATE) + "\", 0)");
                System.out.print(insert);
                try {
                    accesoBD.ejecutaActualizacion(insert);
                } catch (SQLException ex) {
                    Logger.getLogger(AñadirAlumno.class.getName()).log(Level.SEVERE, null, ex);
                }
                int idCuota = 0;
                try {
                    idCuota = getIDCuota();
                } catch (SQLException ex) {
                    Logger.getLogger(AñadirAlumno.class.getName()).log(Level.SEVERE, null, ex);
                }
                String insertAlumno = new String("INSERT INTO pagoactividades (Alumno_idAlumno, Actividades_idActividades, Actividades_Temporada_idTemporada, Cuota_idCuota)"
                        + "VALUES (" + listaIDAlumnos.get(i) + ", " + idActividad + ", " + idTemporada + ", " + idCuota + ")");
                System.out.print(insertAlumno);
                try {
                    accesoBD.ejecutaActualizacion(insertAlumno);
                } catch (SQLException ex) {
                    Logger.getLogger(AñadirAlumno.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            JOptionPane.showMessageDialog(null, "Alumnos insertados",
                    "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
           
        } else {
            JOptionPane.showMessageDialog(null, "No has seleccionado alumnos",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_GuardarActionPerformed

    private void QuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QuitarActionPerformed
        // TODO add your handling code here:
        listaAlumnosQuitados.addAll(AlumnosMostrados.getSelectedValuesList());
        try {
            listaIDAlumnosQuitados = GestorAlumnos.getIdAl(accesoBD, listaAlumnosQuitados);
        } catch (SQLException ex) {
            Logger.getLogger(AñadirAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (listaIDAlumnosQuitados.size() > 0) {
            String delete;
            ResultSet retset;
            for (int i = 0; i < listaIDAlumnosQuitados.size(); i++) {
                delete = "DELETE FROM pagoactividades WHERE Alumno_idAlumno =" + listaIDAlumnosQuitados.get(i);
                boolean exito = accesoBD.eliminar(delete);
                if (!exito) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
             JOptionPane.showMessageDialog(null, "Alumnos eliminados",
                    "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, "No has seleccionado alumnos",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        

    }//GEN-LAST:event_QuitarActionPerformed

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
            java.util.logging.Logger.getLogger(AñadirAlumno.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AñadirAlumno.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AñadirAlumno.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AñadirAlumno.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AñadirAlumno().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList AlumnosMostrados;
    private javax.swing.JLabel AlumnosSelecionados;
    private javax.swing.JButton Añadir;
    private javax.swing.JLabel Buscar;
    private javax.swing.JButton Guardar;
    private javax.swing.JList ListaAlumnos;
    private javax.swing.JButton Mostrar;
    private javax.swing.JLabel Nombre;
    private javax.swing.JTextField NombreTextField;
    private javax.swing.JLabel PrimerApellido;
    private javax.swing.JTextField PrimerApellidoTextField;
    private javax.swing.JButton Quitar;
    private javax.swing.JButton Salir;
    private javax.swing.JLabel SegundoApellido;
    private javax.swing.JTextField SegundoApellidoTextField;
    private javax.swing.JLabel Titulo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
