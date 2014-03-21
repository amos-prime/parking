package com.vattenfall.configuration;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;
import com.vattenfall.ui.MainUI;

@SuppressWarnings("serial")
@WebServlet(value = "/*", asyncSupported = true)
@VaadinServletConfiguration(productionMode = false, ui = MainUI.class, widgetset = "AppWidgetSet")
public class ApplicationVaadinServlet extends VaadinServlet {

}
