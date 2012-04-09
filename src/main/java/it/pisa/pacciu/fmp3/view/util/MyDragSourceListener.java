/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pisa.pacciu.fmp3.view.util;

import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceContext;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;

/**
 *
 * @author m.luly
 */
public class MyDragSourceListener implements DragSourceListener {
    @Override
    public void dragDropEnd(DragSourceDropEvent dragSourceDropEvent) {
      
    }

    @Override
    public void dragEnter(DragSourceDragEvent dragSourceDragEvent) {
      DragSourceContext context = dragSourceDragEvent
          .getDragSourceContext();
      int dropAction = dragSourceDragEvent.getDropAction();
      if ((dropAction & DnDConstants.ACTION_COPY) != 0) {
        context.setCursor(DragSource.DefaultCopyDrop);
      } else if ((dropAction & DnDConstants.ACTION_MOVE) != 0) {
        context.setCursor(DragSource.DefaultMoveDrop);
      } else {
        context.setCursor(DragSource.DefaultCopyNoDrop);
      }
    }

    @Override
    public void dragExit(DragSourceEvent dragSourceEvent) {
    }

    @Override
    public void dragOver(DragSourceDragEvent dragSourceDragEvent) {
    }

    @Override
    public void dropActionChanged(DragSourceDragEvent dragSourceDragEvent) {
    }
  }