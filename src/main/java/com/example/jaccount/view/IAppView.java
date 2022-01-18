package com.example.jaccount.view;

import com.example.jaccount.controller.IController;

public interface IAppView {
    void initialize(IController control);
    String getAmount();
    String getName();
}
