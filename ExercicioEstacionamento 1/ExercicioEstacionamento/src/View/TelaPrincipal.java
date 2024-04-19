package View;

import Controller.*;
import Model.Estacionamento.ContaVeiculo;
import Model.Estacionamento.MetricaCalculoEnum;
import Model.Estacionamento.Serializador;
import Model.Estacionamento.TipoVeiculoEnum;
import Model.Estacionamento.Veiculo;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class TelaPrincipal extends javax.swing.JFrame {

    private Controlador controle;

    public TelaPrincipal() {
        initComponents();
        controle = new Controlador();
        relogio();
        atualizaListaVeiculos();
        carregaContasVeiculosBD();
        inicializaComponentes();
    }

    private void carregaContasVeiculosBD() {
        try {

            String[] colunas = new String[]{"Veiculo", "Placa", "Tipo", "Data Entrada", "Data Saída", "Status"};

            DefaultTableModel tabela = new DefaultTableModel(controle.listaVeiculosCadastrados(), colunas);

            List<ContaVeiculo> contaVeiculoBD = controle.pegaContasCadastradasBD();

            for (ContaVeiculo conta : contaVeiculoBD) {
                tabela.addRow(new Object[]{
                    conta.getVeiculo().getNome(),
                    conta.getVeiculo().getPlaca(),
                    conta.getVeiculo().getTipo(),
                    conta.getInicio(),
                    conta.getFim(),
                    conta.getStatus()
                });
                cboxVeiculos.addItem(conta.getVeiculo().getPlaca()); //atualiza o cboxVeiculos com os dados já cadastrados do BD
            }

            jTable1.setModel(tabela);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }

    private void inicializaComponentes() {
        for (TipoVeiculoEnum tipo : TipoVeiculoEnum.values()) //Carrega os tipos de veiculos no combox
        {
            cBoxTipoVeiculo.addItem(tipo.toString());
        }

        //Carrega o tipo de calcula no combox (1/4 hora, hora, diaria, automático)
    }

    private void relogio() {
        new Thread(() -> {
            while (true) {
                try {
                    labelData.setText(Calendar.getInstance().getTime().toString());
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void atualizaListaVeiculos() {

        try {

            String[] colunas = new String[]{"Veiculo", "Placa", "Tipo", "Data Entrada", "Data Saída", "Status"};

            DefaultTableModel tabela = new DefaultTableModel(controle.listaVeiculosCadastrados(), colunas);

            jTable1.setModel(tabela);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }

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
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cBoxTipoVeiculo = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtbNomeVeiculo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtbPlacaVeiculoEntrada = new javax.swing.JTextField();
        btoInicializar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtbPeriodoPermanencia = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cboxMetricaCalculo = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        txtbValorEstacionamento = new javax.swing.JLabel();
        btoFinalizar = new javax.swing.JButton();
        cboxVeiculos = new javax.swing.JComboBox<>();
        labelData = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Meu Estacionamento");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setText("Escolha o Tipo de Veiculo:");

        cBoxTipoVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cBoxTipoVeiculoActionPerformed(evt);
            }
        });

        jLabel3.setText("Entrada Veiculos");

        jLabel4.setText("Nome do Veiculo:");

        txtbNomeVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbNomeVeiculoActionPerformed(evt);
            }
        });

        jLabel5.setText("Placa do Veiculo:");

        txtbPlacaVeiculoEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbPlacaVeiculoEntradaActionPerformed(evt);
            }
        });

        btoInicializar.setText("Inicializar");
        btoInicializar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btoInicializarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btoInicializar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cBoxTipoVeiculo, 0, 177, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(58, 58, 58)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtbNomeVeiculo)
                            .addComponent(txtbPlacaVeiculoEntrada))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cBoxTipoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtbNomeVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtbPlacaVeiculoEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(btoInicializar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setText("Saída de Veiculos");

        jLabel7.setText("Placa do Veiculo:");

        jLabel8.setText("Periodo de Permanencia");

        txtbPeriodoPermanencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbPeriodoPermanenciaActionPerformed(evt);
            }
        });

        jLabel10.setText("Metricas de Cálculo");

        cboxMetricaCalculo.setEditable(true);
        cboxMetricaCalculo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DIARIA", "HORA", "UM_QUARTO_HORA", "AUTOMATICO" }));
        cboxMetricaCalculo.setSelectedItem("Selecione");
        cboxMetricaCalculo.setToolTipText("");
        cboxMetricaCalculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxMetricaCalculoActionPerformed(evt);
            }
        });

        jLabel11.setText("Valor do Estacionamento:");

        txtbValorEstacionamento.setText("R$ ");

        btoFinalizar.setText("Finalizar");
        btoFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btoFinalizarActionPerformed(evt);
            }
        });

        cboxVeiculos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboxVeiculosItemStateChanged(evt);
            }
        });
        cboxVeiculos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxVeiculosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtbPeriodoPermanencia)
                            .addComponent(cboxVeiculos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboxMetricaCalculo, 0, 209, Short.MAX_VALUE)
                            .addComponent(txtbValorEstacionamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(btoFinalizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(cboxVeiculos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtbPeriodoPermanencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cboxMetricaCalculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(txtbValorEstacionamento, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btoFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        labelData.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        labelData.setText("Data:");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Veiculo", "Placa", "Tipo", "Horário Entrada", "Horário Saída", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jMenu1.setText("File");

        jMenuItem1.setText("Salvar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Abrir");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelData, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelData)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btoInicializarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btoInicializarActionPerformed

        try {

            TipoVeiculoEnum tipoSelecionado = TipoVeiculoEnum.valueOf(cBoxTipoVeiculo.getSelectedItem().toString());

            controle.addContaVeiculo(txtbNomeVeiculo.getText(), txtbPlacaVeiculoEntrada.getText(), tipoSelecionado);
            atualizaListaVeiculos();

            

            cboxVeiculos.addItem(txtbPlacaVeiculoEntrada.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }//GEN-LAST:event_btoInicializarActionPerformed


    private void btoFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btoFinalizarActionPerformed

        try {
            controle.finalizarConta(cboxVeiculos.getSelectedItem());
            atualizaListaVeiculos();
        } catch (Exception ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btoFinalizarActionPerformed


    private void txtbNomeVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbNomeVeiculoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbNomeVeiculoActionPerformed

    private void cboxVeiculosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboxVeiculosItemStateChanged

    }//GEN-LAST:event_cboxVeiculosItemStateChanged

    private void cboxVeiculosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxVeiculosActionPerformed
        try {
            txtbPeriodoPermanencia.setText(controle.calculaPermanencia(cboxVeiculos.getSelectedItem().toString()));
        } catch (Exception ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cboxVeiculosActionPerformed

    private void cboxMetricaCalculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxMetricaCalculoActionPerformed
        String metrica = (String) cboxMetricaCalculo.getSelectedItem();

        try {
            txtbValorEstacionamento.setText(controle.calculoTipo(cboxVeiculos.getSelectedItem().toString(), MetricaCalculoEnum.valueOf(metrica)));

        } catch (Exception ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        //try {
        // controle.finalizarConta(cboxVeiculos.getSelectedItem().toString(), (MetricaCalculoEnum) cboxMetricaCalculo.getSelectedItem());
        //} catch (Exception ex) {
        // Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        // }
        /*Calcula o valor utilizando a metrica de calculo selecionada. */
    }//GEN-LAST:event_cboxMetricaCalculoActionPerformed

    private void txtbPeriodoPermanenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbPeriodoPermanenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbPeriodoPermanenciaActionPerformed


    private void cBoxTipoVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cBoxTipoVeiculoActionPerformed


    }//GEN-LAST:event_cBoxTipoVeiculoActionPerformed

    private void txtbPlacaVeiculoEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbPlacaVeiculoEntradaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbPlacaVeiculoEntradaActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        String filename = File.separator;
        JFileChooser fc = new JFileChooser(new File(filename));
        fc.showSaveDialog(jPanel1);
        File selFile = fc.getSelectedFile();

        try {
            controle.salvar(selFile.getAbsolutePath());

            //implementar uma lista de placas que direcione os valores ao cboxVeiculos. Isto fará com que as placas sejam carregadas adequadamente
            //no cboxVeiculos, ao mesmo tempo que será carregado a tabela.
            //carregaPlaca estará em controle
            //depois é necessário atribuir a lista de placas ao cboxVeiculos.
        } catch (IOException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed

        String filename = File.separator;
        JFileChooser fc = new JFileChooser(new File(filename));
        fc.showOpenDialog(jPanel1);
        File selFile = fc.getSelectedFile();

        try {

            controle.ler(selFile.getAbsolutePath());
            controle.carregaPlaca();
            atualizaListaVeiculos();

            for (int i = 0; i < controle.carregaPlaca().size(); i++) {
                cboxVeiculos.addItem(controle.carregaPlaca().get(i));
            }

        } catch (IOException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        atualizaListaVeiculos();

    }//GEN-LAST:event_jMenuItem2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btoFinalizar;
    private javax.swing.JButton btoInicializar;
    private javax.swing.JComboBox<String> cBoxTipoVeiculo;
    private javax.swing.JComboBox<String> cboxMetricaCalculo;
    private javax.swing.JComboBox<String> cboxVeiculos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JList<String> jList1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel labelData;
    private javax.swing.JTextField txtbNomeVeiculo;
    private javax.swing.JTextField txtbPeriodoPermanencia;
    private javax.swing.JTextField txtbPlacaVeiculoEntrada;
    private javax.swing.JLabel txtbValorEstacionamento;
    // End of variables declaration//GEN-END:variables
}
