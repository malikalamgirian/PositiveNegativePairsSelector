/*
 * PositiveNegativePairsSelectorView.java
 */
package positivenegativepairsselector;

import com.malikalamgirian.qaw.fyp.RandomPositiveNegativeInstancesSelector;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 * The application's main frame.
 */
public class PositiveNegativePairsSelectorView extends FrameView {

    public PositiveNegativePairsSelectorView(SingleFrameApplication app) {
        super(app);

        /*
         * initializations
         */
        chooser = new JFileChooser();

        initComponents();

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {

            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String) (evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer) (evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = PositiveNegativePairsSelectorApp.getApplication().getMainFrame();
            aboutBox = new PositiveNegativePairsSelectorAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        PositiveNegativePairsSelectorApp.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnBrowseInputFile = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNumberOfPositiveInstances = new javax.swing.JTextField();
        txtNumberOfNegativeInstances = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtSuffixToAddToFileName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnBrowseOutputFolder = new javax.swing.JButton();
        btnProcess = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tarStatus = new javax.swing.JTextArea();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        mainPanel.setName("mainPanel"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(positivenegativepairsselector.PositiveNegativePairsSelectorApp.class).getContext().getResourceMap(PositiveNegativePairsSelectorView.class);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel1.border.title"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        btnBrowseInputFile.setText(resourceMap.getString("btnBrowseInputFile.text")); // NOI18N
        btnBrowseInputFile.setName("btnBrowseInputFile"); // NOI18N
        btnBrowseInputFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseInputFileActionPerformed(evt);
            }
        });

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        txtNumberOfPositiveInstances.setText(resourceMap.getString("txtNumberOfPositiveInstances.text")); // NOI18N
        txtNumberOfPositiveInstances.setName("txtNumberOfPositiveInstances"); // NOI18N

        txtNumberOfNegativeInstances.setText(resourceMap.getString("txtNumberOfNegativeInstances.text")); // NOI18N
        txtNumberOfNegativeInstances.setName("txtNumberOfNegativeInstances"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        txtSuffixToAddToFileName.setText(resourceMap.getString("txtSuffixToAddToFileName.text")); // NOI18N
        txtSuffixToAddToFileName.setName("txtSuffixToAddToFileName"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        btnBrowseOutputFolder.setText(resourceMap.getString("btnBrowseOutputFolder.text")); // NOI18N
        btnBrowseOutputFolder.setName("btnBrowseOutputFolder"); // NOI18N
        btnBrowseOutputFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseOutputFolderActionPerformed(evt);
            }
        });

        btnProcess.setFont(resourceMap.getFont("btnProcess.font")); // NOI18N
        btnProcess.setText(resourceMap.getString("btnProcess.text")); // NOI18N
        btnProcess.setName("btnProcess"); // NOI18N
        btnProcess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcessActionPerformed(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tarStatus.setColumns(20);
        tarStatus.setRows(5);
        tarStatus.setName("tarStatus"); // NOI18N
        jScrollPane1.setViewportView(tarStatus);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBrowseInputFile, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNumberOfNegativeInstances, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                            .addComponent(txtNumberOfPositiveInstances, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                            .addComponent(txtSuffixToAddToFileName, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                            .addComponent(btnBrowseOutputFolder, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)))
                    .addComponent(btnProcess, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnBrowseInputFile))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNumberOfPositiveInstances, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNumberOfNegativeInstances, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtSuffixToAddToFileName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBrowseOutputFolder)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(positivenegativepairsselector.PositiveNegativePairsSelectorApp.class).getContext().getActionMap(PositiveNegativePairsSelectorView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 545, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void btnBrowseInputFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseInputFileActionPerformed

        /* Here we just show JfileChooser and select the file */
        tarStatus.setText(tarStatus.getText() + "\nbtnBrowseInputFile : Browse txt file pressed. \n");

        String chooser_Title = "Select .TXT dataset File";

        try {
            chooser.setDialogTitle(chooser_Title);

            /* Open a file dialog. */
            int retval = chooser.showOpenDialog(mainPanel);

            if (retval == JFileChooser.APPROVE_OPTION) {
                //... The user selected a file, get it, use it.
                File file = chooser.getSelectedFile();

                // check if the selected file is .txt
                if (file.getName().toLowerCase().endsWith(".txt")) {

                    //... Update "input_TXT_File_Path_Name" for process Button.
                    this.input_TXT_File_Path_Name = file.getAbsolutePath();

                    tarStatus.setText(tarStatus.getText() + "File : '" + input_TXT_File_Path_Name + "' : Choosen Successfully. Now set proper selection values. \n");

                } else {
                    // ask user to select .xml file
                    tarStatus.setText(tarStatus.getText() + "Please choose proper .TXT file to make Random Selections. \n");
                }
            }

        } catch (Exception ex) {
            tarStatus.setText(tarStatus.getText() + ex + "\n");
            tarStatus.setText(tarStatus.getText() + ex.getMessage() + "\n");
        }
    }//GEN-LAST:event_btnBrowseInputFileActionPerformed

    private void btnBrowseOutputFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseOutputFolderActionPerformed

        /* Here we just show JfileChooser and select the folder */
        tarStatus.setText(tarStatus.getText() + "\nbtnBrowseOutputFolder : btn Browse Outpu tFolder pressed. \n");

        String chooser_Title = "Select Directory Where to save output file";

        try {
            chooser = new JFileChooser();
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setDialogTitle(chooser_Title);
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            // disable the "All files" option.
            chooser.setAcceptAllFileFilterUsed(false);

            if (chooser.showOpenDialog(mainPanel) == JFileChooser.APPROVE_OPTION) {

                /* Set URL output folder */
                this.output_Folder_Path_Name_URL = chooser.getSelectedFile().getAbsolutePath();

                /* selection proper */
                tarStatus.setText(tarStatus.getText() + "You have selected : '" + output_Folder_Path_Name_URL + "' folder. \n");

            } else {
                tarStatus.setText(tarStatus.getText() + "Please choose folder. \n");
            }

        } catch (Exception ex) {
            tarStatus.setText(tarStatus.getText() + ex + "\n");
            tarStatus.setText(tarStatus.getText() + ex.getMessage() + "\n");
        }
    }//GEN-LAST:event_btnBrowseOutputFolderActionPerformed

    private void btnProcessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcessActionPerformed

        /* Here we just show JfileChooser and select the folder */
        tarStatus.setText(tarStatus.getText() + "\nbtnProcess : btn Process pressed. \n");

        try {
            /*
             * Select and Set Parameters
             */
            this.number_Of_0_pairs = Integer.parseInt(txtNumberOfNegativeInstances.getText());
            this.number_Of_1_pairs = Integer.parseInt(txtNumberOfPositiveInstances.getText());
            this.suffix_To_Add_To_Filename = txtSuffixToAddToFileName.getText();

            /*
             * Call Helper Class
             */
            randomPositiveNegativeInstancesSelector = new RandomPositiveNegativeInstancesSelector(number_Of_1_pairs, number_Of_0_pairs, output_Folder_Path_Name_URL, suffix_To_Add_To_Filename, input_TXT_File_Path_Name);


            tarStatus.setText(tarStatus.getText() + "Selections Made Successfully. Saved in : " + output_Folder_Path_Name_URL + " \n");

        } catch (Exception ex) {
            tarStatus.setText(tarStatus.getText() + ex + "\n");
            tarStatus.setText(tarStatus.getText() + ex.getMessage() + "\n");
        }

    }//GEN-LAST:event_btnProcessActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBrowseInputFile;
    private javax.swing.JButton btnBrowseOutputFolder;
    private javax.swing.JButton btnProcess;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JTextArea tarStatus;
    private javax.swing.JTextField txtNumberOfNegativeInstances;
    private javax.swing.JTextField txtNumberOfPositiveInstances;
    private javax.swing.JTextField txtSuffixToAddToFileName;
    // End of variables declaration//GEN-END:variables
    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;
    private JDialog aboutBox;

    /*
     * Declarations
     */
    private int number_Of_1_pairs = 0,
            number_Of_0_pairs = 0;
    private String output_Folder_Path_Name_URL = null,
            suffix_To_Add_To_Filename = null,
            input_TXT_File_Path_Name = null;
    private JFileChooser chooser;

    /*
     * Helper class
     */
    RandomPositiveNegativeInstancesSelector randomPositiveNegativeInstancesSelector;
}
