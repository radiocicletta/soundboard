/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pisa.pacciu.fmp3.view.util;

import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author m.luly
 */
public class TreeDragGestureListener implements DragGestureListener {
    public void dragGestureRecognized(DragGestureEvent dragGestureEvent) {
      // Can only drag leafs
      JTree tree = (JTree) dragGestureEvent.getComponent();
      TreePath path = tree.getSelectionPath();
      if (path == null) {
        // Nothing selected, nothing to drag
        tree.getToolkit().beep();
      } else {
        DefaultMutableTreeNode selection = (DefaultMutableTreeNode) path
            .getLastPathComponent();
        if (selection.isLeaf()) {
          TransferableTreeNode node = new TransferableTreeNode(
              selection);
          dragGestureEvent.startDrag(DragSource.DefaultCopyDrop,
              node, new MyDragSourceListener());
        } else {
          tree.getToolkit().beep();
        }
      }
    }
  }