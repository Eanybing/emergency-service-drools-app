/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PhoneCallsPanel.java
 *
 * Created on Nov 24, 2010, 3:15:17 PM
 */

package com.wordpress.salaboy.ui;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.table.DefaultTableModel;
import org.jbpm.task.Content;
import org.jbpm.task.Task;
import org.jbpm.task.TaskData;
import org.jbpm.task.query.TaskSummary;
import org.jbpm.task.service.responsehandlers.BlockingGetContentResponseHandler;
import org.jbpm.task.service.responsehandlers.BlockingGetTaskResponseHandler;
import org.jbpm.task.service.responsehandlers.BlockingTaskSummaryResponseHandler;
import com.wordpress.salaboy.model.Emergency.EmergencyType;

/**
 *
 * @author esteban
 */
public class AmbulanceControlPanel extends javax.swing.JPanel implements Refreshable{

    private UserTaskListUI parent;
    
    /** Creates new form PhoneCallsPanel */
    public AmbulanceControlPanel(UserTaskListUI parent) {
        this.parent = parent;
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        phoneCallsJScrollPane = new javax.swing.JScrollPane();
        ambulanceControlsJTable = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        refreshJButton = new javax.swing.JButton();
        ftxt_refreshSeconds = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        chk_autoRefresh = new javax.swing.JCheckBox();

        setName("Control Operator"); // NOI18N
        setPreferredSize(new java.awt.Dimension(300, 480));

        ambulanceControlsJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id","Incoming Call",
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Number.class,
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        ambulanceControlsJTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        ambulanceControlsJTable.setName("Control Operator"); // NOI18N
        ambulanceControlsJTable.setPreferredSize(new java.awt.Dimension(280, 100));
        ambulanceControlsJTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ambulanceControlsJTablerowClick(evt);
            }
        });
        phoneCallsJScrollPane.setViewportView(ambulanceControlsJTable);

        jLabel11.setText("Ambulance for Emergencies");

        refreshJButton.setText("Refresh");
        refreshJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshJButtonActionPerformed(evt);
            }
        });

        ftxt_refreshSeconds.setText("3");

        jLabel1.setText("secs");

        chk_autoRefresh.setText("auto refresh");
        chk_autoRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_autoRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jLabel11))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(phoneCallsJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(refreshJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ftxt_refreshSeconds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chk_autoRefresh)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(phoneCallsJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(refreshJButton)
                    .addComponent(jLabel1)
                    .addComponent(chk_autoRefresh)
                    .addComponent(ftxt_refreshSeconds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ambulanceControlsJTablerowClick(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ambulanceControlsJTablerowClick
        //System.out.println("ID from EVT"+evt.getID());
        int selected = ambulanceControlsJTable.rowAtPoint(evt.getPoint());
        Long id = Long.parseLong(ambulanceControlsJTable.getModel().getValueAt(selected, 0).toString());
        this.ambulanceSelected(id);
        
    }//GEN-LAST:event_ambulanceControlsJTablerowClick

    private void refreshJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshJButtonActionPerformed
        this.refresh();
}//GEN-LAST:event_refreshJButtonActionPerformed

    private void chk_autoRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_autoRefreshActionPerformed
        if(chk_autoRefresh.isSelected()){
            UIJTableRefreshManager.start(chk_autoRefresh, Integer.valueOf(ftxt_refreshSeconds.getText()), this);
        }
    }//GEN-LAST:event_chk_autoRefreshActionPerformed
    
    public void refresh(){

        BlockingTaskSummaryResponseHandler handler = new BlockingTaskSummaryResponseHandler();
        
        this.parent.getTaskClient().getTasksAssignedAsPotentialOwner("control_operator", "en-UK", handler);
        
        List<TaskSummary> results = handler.getResults();
        
        DefaultTableModel tableModel = ((DefaultTableModel)ambulanceControlsJTable.getModel());
        
        int rowCount = tableModel.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            tableModel.removeRow(0);
        }
        
        for (TaskSummary summary : results) {
            long id = summary.getId();
            String name = summary.getName();
            tableModel.addRow(new Object[]{id,name});
        }
    }
    
    private JDialog callPopup;
    public void ambulanceSelected(Long id) {

        String taskinfo = "";
        
        try {
            ObjectInputStream ois = null;
            //Show ambulance tab.. with all the selected items
            BlockingTaskSummaryResponseHandler handler = new BlockingTaskSummaryResponseHandler();
            this.parent.getTaskClient().getTasksAssignedAsPotentialOwner("control_operator", "en-UK", handler);
            List<TaskSummary> taskSums = handler.getResults();
            TaskSummary taskSum = taskSums.get(0);
            
            BlockingGetTaskResponseHandler handlerT = new BlockingGetTaskResponseHandler();
            this.parent.getTaskClient().getTask(taskSum.getId(), handlerT);
            Task task2 = handlerT.getTask();
            TaskData taskData = task2.getTaskData();
            //System.out.println("TaskData = " + taskData);
            BlockingGetContentResponseHandler handlerC = new BlockingGetContentResponseHandler();
            this.parent.getTaskClient().getContent(taskData.getDocumentContentId(), handlerC);
            Content content = handlerC.getContent();
            //System.out.println("Content= " + content);
            ByteArrayInputStream bais = new ByteArrayInputStream(content.getContent());
            ois = new ObjectInputStream(bais);
            taskinfo = (String) ois.readObject();
        } catch (Exception ex) {
            Logger.getLogger(UserTaskListUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        AmbulancePanel ambulancePanel = new AmbulancePanel(this);
        ambulancePanel.configurePanel(taskinfo);
        callPopup = new JDialog(this.parent, "Info",true);
        callPopup.add(ambulancePanel);
        this.callPopup.setSize(300, 430);
        this.callPopup.setVisible(true);
        this.callPopup.requestFocus();
    }
    
    public void sendAmbulance() throws IOException, ClassNotFoundException{
        this.callPopup.setVisible(false);
        
        BlockingTaskSummaryResponseHandler handler = new BlockingTaskSummaryResponseHandler();
        this.parent.getTaskClient().getTasksAssignedAsPotentialOwner("control_operator", "en-UK", handler);
        List<TaskSummary> taskSums = handler.getResults();
        TaskSummary taskSum = taskSums.get(0);
        // se llama dos veces al mismo start.. que raro!!!!!!!
        this.parent.getTaskClient().start(taskSum.getId(), "control_operator", null);
        BlockingGetTaskResponseHandler handlerT = new BlockingGetTaskResponseHandler();
        this.parent.getTaskClient().getTask(taskSum.getId(), handlerT);
        Task task2 = handlerT.getTask();
        TaskData taskData = task2.getTaskData();

        System.out.println("TaskData = " + taskData);
        BlockingGetContentResponseHandler handlerC = new BlockingGetContentResponseHandler();
        this.parent.getTaskClient().getContent(taskData.getDocumentContentId(), handlerC);
        Content content = handlerC.getContent();

        System.out.println("Content= " + content);
        ByteArrayInputStream bais = new ByteArrayInputStream(content.getContent());

        ObjectInputStream ois = new ObjectInputStream(bais);
        //#{doctor.id}, #{ambulance.id},  #{patient.id}, #{patient.name}, #{patient.age}, #{patient.gender}, #{emergency.location}, #{emergency.type}
        String[] taskinfo = ((String) ois.readObject()).split(",");

        Long ambulanceId = Long.valueOf(taskinfo[1].trim());

        this.parent.getTaskClient().complete(taskSum.getId(), "control_operator", null, null);
                
        this.parent.sendAmbulance(EmergencyType.valueOf(taskinfo[7].trim()), ambulanceId);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable ambulanceControlsJTable;
    private javax.swing.JCheckBox chk_autoRefresh;
    private javax.swing.JFormattedTextField ftxt_refreshSeconds;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JScrollPane phoneCallsJScrollPane;
    private javax.swing.JButton refreshJButton;
    // End of variables declaration//GEN-END:variables


}
