package com.vattenfall.ui;

import java.util.List;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vattenfall.model.User;
import com.vattenfall.services.UserService;

/**
 * Main UI
 */
@Theme("mytheme")
public class MainUI extends UI {

    private static final long serialVersionUID = 1L;

    /** Spring helper */
    private final SpringContextHelper helper;

    public MainUI() {
        helper = new SpringContextHelper(VaadinServlet.getCurrent().getServletContext());
    }

    @Override
    protected void init(VaadinRequest request) {
        UserService userService = helper.getBean(UserService.class);
        List<User> allUsers = userService.findAll();
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setMargin(true);
        for (User user : allUsers) {
            verticalLayout.addComponent(new Label(user.getUsername()));
        }
        setContent(verticalLayout);
    }

}
