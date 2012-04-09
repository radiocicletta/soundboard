/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pisa.pacciu.fmp3.view.util;

import it.pisa.pacciu.fmp3.service.HistoryService;
import it.pisa.pacciu.fmp3.service.MainService;
import javax.swing.JFrame;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author m.luly
 */
public class TableValueInserted implements TableModelListener {

    Boolean changing = Boolean.FALSE;
    
    

    @Override
    public void tableChanged(TableModelEvent e) {
        if (e.getType() == TableModelEvent.UPDATE) {
            DefaultTableModel model = (DefaultTableModel) e.getSource();
            synchronized (model) {
                if (!changing) {
                    changing=Boolean.TRUE;
                    String val = model.getValueAt(e.getFirstRow(), e.getColumn()).toString();
                    if (val != null && val.trim().length() > 0) {
                        char firstVal = val.charAt(0);
                        String path=model.getValueAt(e.getFirstRow(), 0).toString();
                        MainService.getInstance().addFile(firstVal, path);
                        HistoryService.getInstance().addFileLoaded(""+firstVal, path);
                        model.setValueAt("" + firstVal, e.getFirstRow(), e.getColumn());
                    }
                    changing=Boolean.FALSE;
                }
            }
        }
    }
}
