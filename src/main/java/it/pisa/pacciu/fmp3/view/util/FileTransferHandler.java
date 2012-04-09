/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pisa.pacciu.fmp3.view.util;

import java.awt.Rectangle;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import javax.swing.JTable;
import javax.swing.TransferHandler;
import javax.swing.TransferHandler.TransferSupport;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author m.luly
 */
public class FileTransferHandler extends TransferHandler {
    JTable table;
    DefaultTableModel tableModel;

    public FileTransferHandler(JTable table) {
        super();
        this.table = table;
        this.tableModel = (DefaultTableModel) table.getModel();
    }
    

    @Override
    public boolean canImport(TransferSupport support) {
        // for the demo, we'll only support drops (not clipboard paste)
        if (!support.isDrop()) {
            return false;
        }

        // we only import Strings
        if (!support.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            return false;
        }

        return true;
    }

    @Override
    public boolean importData(TransferSupport support) {
        // if we can't handle the import, say so
        if (!canImport(support)) {
            return false;
        }

        // fetch the drop location
        JTable.DropLocation dl = (JTable.DropLocation) support.getDropLocation();

        int row = dl.getRow();

        // fetch the data and bail if this fails
        String data;
        try {
            TransferableTreeNode treeNode=(TransferableTreeNode) support.getTransferable().getTransferData(
                    TransferableTreeNode.DEFAULT_MUTABLE_TREENODE_FLAVOR);
            
            data = treeNode.getParent().toString()+File.separator+treeNode.toString();
        } catch (UnsupportedFlavorException e) {
            return false;
        } catch (IOException e) {
            return false;
        }

        String[] rowData = new String[]{data,""};
        tableModel.insertRow(row, rowData);

        Rectangle rect = table.getCellRect(row, 0, false);
        if (rect != null) {
            table.scrollRectToVisible(rect);
        }
        return true;
    }
}
