package com.example.jaccount.controller;

import com.example.jaccount.model.IModel;
import com.example.jaccount.view.IAppView;

public interface IController {
    public void setModel(IModel modelIn);
    public void setView(IAppView viewIn);
}
