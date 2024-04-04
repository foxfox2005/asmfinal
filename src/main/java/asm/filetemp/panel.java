package asm.filetemp;

import java.awt.event.*;

import asm.FileManager;
import model.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class panel extends javax.swing.JPanel {
    DefaultTableModel tableModel;
    List<Employee> EmployeeList = new ArrayList<>();
    FileManager fileManager = FileManager.getInstance();
    int index = -1;

    public panel() {
        initComponents();
        tablelSet();
        initData();
        sortNV();
        fillToTable();
    }

    public void initData() {
        File file = new File("data.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            List<Employee> employees = FileManager.readFile(FileManager.chooseFile());
            EmployeeList.addAll(employees);
        }
    }

    public void tablelSet() {
        tableModel = (DefaultTableModel) tblALl.getModel();
        tableModel.setColumnIdentifiers(new String[]{"Mã", "Họ và Tên", "Tuổi", "Email", "Lương"});

    }

    public Employee readForm() {
        if (txtMaNV.getText().isEmpty() || txtHoTen.getText().isEmpty() || txtTuoi.getText().isEmpty() || txtMail.getText().isEmpty() || txtLuong.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống");
            return null;
        }

        if (!txtTuoi.getText().matches("\\d+") || !txtLuong.getText().matches("\\d.+")) {
            JOptionPane.showMessageDialog(this, "Tuổi và lương phải là số");
            return null;
        }

        if (!txtMail.getText().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            JOptionPane.showMessageDialog(this, "Email không hợp lệ");
            return null;
        }

        return new Employee(txtMaNV.getText(), txtHoTen.getText(), Integer.valueOf(txtTuoi.getText()), txtMail.getText(), Double.parseDouble(txtLuong.getText()));
    }

    public void fillToTable() {
        tableModel.setRowCount(0); // -1 for remove blank row
        for (Employee emp : EmployeeList) {
            tableModel.addRow(new Object[]{emp.getMaNV(), emp.getHoTen(), emp.getTuoi(), emp.getEmail(), emp.getLuong()});
        }
    }

    public void sortNV() {
        Collections.sort(EmployeeList, ((emp1, emp2) -> (emp1.getMaNV().compareTo(emp2.getMaNV()))));
        fillToTable();
    }

    public Employee idNv(String id) {
        for (Employee emp : EmployeeList) {
            if (emp.getMaNV().equalsIgnoreCase(id)) {
                return emp;
            }
        }
        return null;
    }

    public void selectRow(int index) {
        txtHoTen.setText(EmployeeList.get(index).getHoTen());
        txtMaNV.setText(EmployeeList.get(index).getMaNV());
        txtMail.setText(EmployeeList.get(index).getEmail());
        txtTuoi.setText(String.valueOf(EmployeeList.get(index).getTuoi()));
        txtLuong.setText(String.valueOf(EmployeeList.get(index).getLuong()));
    }

    //button
    public void newButton() {
        txtHoTen.setText("");
        txtMaNV.setText("");
        txtLuong.setText("");
        txtTuoi.setText("");
        txtMail.setText("");
        index = -1;
    }

    public void saveButton() {
        Employee EmmployeeFormEdit = readForm();
        int EmployeeIndex = EmployeeList.indexOf(idNv(EmmployeeFormEdit.getMaNV()));

        if (index == -1 || EmployeeIndex == -1) {
            EmployeeList.add(EmmployeeFormEdit);
        } else {
            EmployeeList.set(index, new Employee(EmmployeeFormEdit.getMaNV(), EmmployeeFormEdit.getHoTen(), EmmployeeFormEdit.getTuoi(), EmmployeeFormEdit.getEmail(), EmmployeeFormEdit.getLuong()));
        }

        fillToTable();
    }

    public void deleteButton() {
        EmployeeList.remove(index);
        fillToTable();
        newButton();
        JOptionPane.showMessageDialog(this, "Đã Xoá");
    }

    public void findButton() {
        String id = JOptionPane.showInputDialog("Nhập mã nhân viên cần tìm");
        Employee emp = idNv(id);
        if (emp != null) {
            index = EmployeeList.indexOf(emp);
            selectRow(index);
            tblALl.setRowSelectionInterval(index, index);
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy");
        }
    }

    public void openButton() {
        File file = FileManager.chooseFile();
        if (file != null) {
            EmployeeList = new FileManager().readFile(file);
            fillToTable();
        }
    }

    public void exitButton() {
        fileManager.writeFile((ArrayList<Employee>) EmployeeList);
        System.exit(0);
    }




    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel1 = new JLabel();
        jPanel1 = new JPanel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        txtMaNV = new JTextField();
        txtHoTen = new JTextField();
        txtTuoi = new JTextField();
        txtMail = new JTextField();
        txtLuong = new JTextField();
        jPanel2 = new JPanel();
        btnNew = new JButton();
        btnSave = new JButton();
        btnDelete = new JButton();
        btnFind = new JButton();
        btnOpen = new JButton();
        BtnExit = new JButton();
        jScrollPane1 = new JScrollPane();
        tblALl = new JTable();
        jPanel3 = new JPanel();
        btnTop = new JButton();
        btnRight = new JButton();
        btnNext = new JButton();
        btnPrev = new JButton();

        //======== this ========
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            @Override
            public void propertyChange(java.beans.PropertyChangeEvent e
            ) {
                if ("\u0062order".equals(e.getPropertyName())) throw new RuntimeException();
            }
        });

        //---- jLabel1 ----
        jLabel1.setText("Qu\u1ea3n L\u00fd Nh\u00e2n Vi\u00ean");

        //======== jPanel1 ========
        {

            //---- jLabel2 ----
            jLabel2.setText("M\u00e3 nh\u00e2n vi\u00ean");

            //---- jLabel3 ----
            jLabel3.setText("H\u1ecd v\u00e0 t\u00ean");

            //---- jLabel4 ----
            jLabel4.setText("Tu\u1ed5i");

            //---- jLabel5 ----
            jLabel5.setText("Mail");

            //---- jLabel6 ----
            jLabel6.setText("L\u01b0\u01a1ng");

            //---- txtMaNV ----
            txtMaNV.setToolTipText("");
            txtMaNV.addActionListener(e -> txtMaNVActionPerformed(e));

            GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                    jPanel1Layout.createParallelGroup()
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(14, 14, 14)
                                    .addGroup(jPanel1Layout.createParallelGroup()
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel6))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtMaNV)
                                            .addComponent(txtHoTen)
                                            .addComponent(txtTuoi)
                                            .addComponent(txtMail)
                                            .addComponent(txtLuong, GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE))
                                    .addContainerGap(79, Short.MAX_VALUE))
            );
            jPanel1Layout.setVerticalGroup(
                    jPanel1Layout.createParallelGroup()
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel2)
                                            .addComponent(txtMaNV, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel3)
                                            .addComponent(txtHoTen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel4)
                                            .addComponent(txtTuoi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel5)
                                            .addComponent(txtMail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel6)
                                            .addComponent(txtLuong, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
        }

        //======== jPanel2 ========
        {

            //---- btnNew ----
            btnNew.setText("New");
            btnNew.addActionListener(e -> btnNewActionPerformed(e));

            //---- btnSave ----
            btnSave.setText("Save");
            btnSave.addActionListener(e -> btnSaveActionPerformed(e));

            //---- btnDelete ----
            btnDelete.setText("Delete");
            btnDelete.addActionListener(e -> btnDeleteActionPerformed(e));

            //---- btnFind ----
            btnFind.setText("Find");
            btnFind.addActionListener(e -> btnFindActionPerformed(e));

            //---- btnOpen ----
            btnOpen.setText("Open");
            btnOpen.addActionListener(e -> btnOpenActionPerformed(e));

            //---- BtnExit ----
            BtnExit.setText("Exit");
            BtnExit.addActionListener(e -> BtnExitActionPerformed(e));

            GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
            jPanel2.setLayout(jPanel2Layout);
            jPanel2Layout.setHorizontalGroup(
                    jPanel2Layout.createParallelGroup()
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(33, 33, 33)
                                    .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                            .addComponent(BtnExit)
                                            .addComponent(btnFind)
                                            .addComponent(btnDelete)
                                            .addComponent(btnSave)
                                            .addComponent(btnNew)
                                            .addComponent(btnOpen))
                                    .addContainerGap(29, Short.MAX_VALUE))
            );
            jPanel2Layout.setVerticalGroup(
                    jPanel2Layout.createParallelGroup()
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(btnNew)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnSave)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnDelete)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnFind)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnOpen)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(BtnExit)
                                    .addContainerGap(20, Short.MAX_VALUE))
            );
        }

        //======== jScrollPane1 ========
        {

            //---- tblALl ----
            tblALl.setModel(new DefaultTableModel(
                    new Object[][]{
                            {null, null, null, null, null},
                            {null, null, null, null, null},
                            {null, null, null, null, null},
                            {null, null, null, null, null},
                    },
                    new String[]{
                            "M\u00e3", "H\u1ecd T\u00ean", "Tu\u1ed5i", "email", "L\u01b0\u01a1ng"
                    }
            ));
            tblALl.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    tblALlMouseClicked(e);
                }
            });
            jScrollPane1.setViewportView(tblALl);
        }

        //======== jPanel3 ========
        {

            //---- btnLeft ----
            btnTop.setText("|<");
            btnTop.addActionListener(e -> btnTopAction(e));

            //---- btnRight ----
            btnRight.setText(">|");
            btnRight.addActionListener(e -> btnRightAction(e));

            //---- btnThua2 ----
            btnNext.setText(">>");
            btnNext.addActionListener(e -> btnNextAction(e));

            //---- btnThua ----
            btnPrev.setText("<<");
            btnPrev.addActionListener(e -> btnPrevAction(e));

            GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
            jPanel3.setLayout(jPanel3Layout);
            jPanel3Layout.setHorizontalGroup(
                    jPanel3Layout.createParallelGroup()
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(26, 26, 26)
                                    .addComponent(btnTop, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnPrev, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                    .addGap(12, 12, 12)
                                    .addComponent(btnNext, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnRight, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(19, Short.MAX_VALUE))
            );
            jPanel3Layout.setVerticalGroup(
                    jPanel3Layout.createParallelGroup()
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(btnTop)
                                            .addComponent(btnRight)
                                            .addComponent(btnPrev)
                                            .addComponent(btnNext))
                                    .addContainerGap(29, Short.MAX_VALUE))
            );
        }

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup()
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(87, 87, 87)
                                                .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(22, 22, 22)
                                                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 541, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(29, Short.MAX_VALUE))
        );
    }

    private void txtMaNVActionPerformed(java.awt.event.ActionEvent evt) {

    }

    //button table
    private void btnTopAction(java.awt.event.ActionEvent evt) {
        index = 0;
        selectRow(index);
        tblALl.setRowSelectionInterval(index, index);

    }

    private void btnRightAction(java.awt.event.ActionEvent evt) {
        index = EmployeeList.size() - 1;
        selectRow(index);
        tblALl.setRowSelectionInterval(index, index);

    }

    private void btnNextAction(java.awt.event.ActionEvent evt) {
        if (index < EmployeeList.size() - 1) {
            index++;
            selectRow(index);
            tblALl.setRowSelectionInterval(index, index);

        }
    }

    private void btnPrevAction(java.awt.event.ActionEvent evt) {
        if (index > 0) {
            index--;
            selectRow(index);
            tblALl.setRowSelectionInterval(index, index);

        }
    }


    //button Function
    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        newButton();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        saveButton();
        sortNV();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        deleteButton();
        sortNV();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        findButton();
    }//GEN-LAST:event_btnFindActionPerformed

    private void btnOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenActionPerformed
        openButton();
    }//GEN-LAST:event_btnOpenActionPerformed

    private void BtnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExitActionPerformed
        exitButton();
    }//GEN-LAST:event_BtnExitActionPerformed

    private void tblALlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblALlMouseClicked
        index = tblALl.getSelectedRow();
        selectRow(index);
    }//GEN-LAST:event_tblALlMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel jLabel1;
    private JPanel jPanel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JTextField txtMaNV;
    private JTextField txtHoTen;
    private JTextField txtTuoi;
    private JTextField txtMail;
    private JTextField txtLuong;
    private JPanel jPanel2;
    private JButton btnNew;
    private JButton btnSave;
    private JButton btnDelete;
    private JButton btnFind;
    private JButton btnOpen;
    private JButton BtnExit;
    private JScrollPane jScrollPane1;
    private JTable tblALl;
    private JPanel jPanel3;
    private JButton btnTop;
    private JButton btnRight;
    private JButton btnNext;
    private JButton btnPrev;
    // End of variables declaration//GEN-END:variables
}
