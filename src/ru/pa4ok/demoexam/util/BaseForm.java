package ru.pa4ok.demoexam.util;

import javax.swing.*;
import java.awt.*;

public class BaseForm extends JFrame
{
    public static final String APP_TITLE = "Application";

    public BaseForm(int width, int height)
    {
        setTitle(APP_TITLE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(width, height));
        setLocation(
                Toolkit.getDefaultToolkit().getScreenSize().width / 2 - width / 2,
                Toolkit.getDefaultToolkit().getScreenSize().height / 2 - height / 2
        );
    }
}
