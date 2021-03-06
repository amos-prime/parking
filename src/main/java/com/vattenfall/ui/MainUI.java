package com.vattenfall.ui;

import java.util.LinkedList;
import java.util.List;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vattenfall.model.User;
import com.vattenfall.model.UserStatus;
import com.vattenfall.services.UserService;

/**
 * Main UI
 */
@Theme("mytheme")
public class MainUI extends UI {

    private static final long serialVersionUID = 1L;

    /**
     * Spring helper
     */
    private final SpringContextHelper helper;

    public MainUI() {
        helper = new SpringContextHelper(VaadinServlet.getCurrent().getServletContext());
    }

    @Override
    protected void init(VaadinRequest request) {
        UserService userService = helper.getBean(UserService.class);
        List<User> allUsers = userService.findAll();
        if (allUsers == null || allUsers.isEmpty()) {
            allUsers = initData();
            for (User user : allUsers) {
                userService.create(user);
            }
        }
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setMargin(true);
        for (User user : allUsers) {
            verticalLayout.addComponent(new Label(user.getUsername()));
        }
        setContent(verticalLayout);
    }

    public List<User> initData() {
        List<User> users = new LinkedList<User>();
        for (int i = 1; i < 5; i++) {
            User user = new User();
            user.setUsername("User" + i);
            user.setPassword("pass");
            user.setStatus(UserStatus.HOLDER);
            users.add(user);
        }
        return users;
    }

}
