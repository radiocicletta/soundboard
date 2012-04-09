/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pisa.pacciu.fmp3.view.util;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.tree.DefaultMutableTreeNode;

public class TransferableTreeNode extends DefaultMutableTreeNode implements
        Transferable,Serializable {

    final static int TREE = 0;
    final static int STRING = 1;
    final static int PLAIN_TEXT = 1;
    final public static DataFlavor DEFAULT_MUTABLE_TREENODE_FLAVOR = new DataFlavor(
            DefaultMutableTreeNode.class, "Default Mutable Tree Node");
    static DataFlavor flavors[] = {DEFAULT_MUTABLE_TREENODE_FLAVOR,
        DataFlavor.stringFlavor};
    private DefaultMutableTreeNode data;

    public TransferableTreeNode(String data) {
        this(new DefaultMutableTreeNode(data));
    }

    
    public TransferableTreeNode(DefaultMutableTreeNode data) {
        super(data);
        this.data = data;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return flavors;
    }

    @Override
    public Object getTransferData(DataFlavor flavor)
            throws UnsupportedFlavorException, IOException {
        Object returnObject;
        if (flavor.equals(flavors[TREE])) {
//            Object userObject = data.getUserObject();
//            if (userObject == null) {
                returnObject = data;
//            } else {
//                returnObject = userObject;
//            }
        } else if (flavor.equals(flavors[STRING])) {
            Object userObject = data.getUserObject();
            if (userObject == null) {
                returnObject = data.toString();
            } else {
                returnObject = userObject.toString();
            }
        } else if (flavor.equals(flavors[PLAIN_TEXT])) {
            Object userObject = data.getUserObject();
            String string;
            if (userObject == null) {
                string = data.toString();
            } else {
                string = userObject.toString();
            }
            returnObject = new ByteArrayInputStream(string.getBytes("Unicode"));
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
        return returnObject;
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        boolean returnValue = false;
        for (int i = 0, n = flavors.length; i < n; i++) {
            if (flavor.equals(flavors[i])) {
                returnValue = true;
                break;
            }
        }
        return returnValue;
    }
}