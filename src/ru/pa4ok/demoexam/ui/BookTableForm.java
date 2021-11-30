package ru.pa4ok.demoexam.ui;

import ru.pa4ok.demoexam.entity.BookEntity;
import ru.pa4ok.demoexam.manager.BookEntityManager;
import ru.pa4ok.demoexam.util.BaseForm;
import ru.pa4ok.demoexam.util.CustomTableModel;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Predicate;

public class BookTableForm extends BaseForm
{
    private JPanel mainPanel;
    private JTable table;
    private JButton addButton;
    private JComboBox<String> authorSortBox;
    private JButton idSortButton;
    private JButton dateSortButton;
    private JComboBox pageSortBox;
    private JButton clearFiltersButton;

    private CustomTableModel<BookEntity> model;

    private boolean idSort = true;
    private boolean dateSort = false;

    public BookTableForm()
    {
        super(800, 600);
        setContentPane(mainPanel);

        initTable();
        initBoxes();
        initButtons();

        setVisible(true);
    }

    private void initTable()
    {
        table.getTableHeader().setReorderingAllowed(false);

        try {
            model = new CustomTableModel<>(
                    BookEntity.class,
                    new String[] { "ID", "Название", "Автор", "Страниц", "Дата написания" },
                    BookEntityManager.selectAll()
            );
            table.setModel(model);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2) {
                    int row = table.rowAtPoint(e.getPoint());
                    dispose();
                    new BookEditForm(model.getRows().get(row));
                }
            }
        });

        table.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_DELETE) {
                    int row = table.getSelectedRow();
                    if(row != -1) {
                        if(JOptionPane.showConfirmDialog(BookTableForm.this, "Вы точно хотите удалить данную запись?", "Подтверждение", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                            try {
                                BookEntityManager.delete(model.getRows().get(row));
                                model.getRows().remove(row);
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
    }

    private void initBoxes()
    {
        authorSortBox.addItem("Выберите автора");
        try {
            Set<String> authors = new HashSet<>();
            for(BookEntity b : BookEntityManager.selectAll()) {
                authors.add(b.getAuthor());
            }
            for(String s : authors) {
                authorSortBox.addItem(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        authorSortBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {
                    applyFilters();
                }
            }
        });

        pageSortBox.addItem("Любое количество страниц");
        pageSortBox.addItem(5);
        pageSortBox.addItem(10);
        pageSortBox.addItem(1000);

        pageSortBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {
                    applyFilters();
                }
            }
        });
    }

    private void applyFilters()
    {
        try {
            List<BookEntity> allRows = BookEntityManager.selectAll();

            if(authorSortBox.getSelectedIndex() != 0) {
                allRows.removeIf(b -> !b.getAuthor().equals(authorSortBox.getSelectedItem()));
            }

            if(pageSortBox.getSelectedIndex() != 0) {
                int count = (int) pageSortBox.getSelectedItem();
                allRows.removeIf(b -> b.getPages() < count);
            }

            idSort = true;
            dateSort = false;

            model.setRows(allRows);
            model.fireTableDataChanged();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void initButtons()
    {
        addButton.addActionListener(e -> {
            dispose();
            new BookCreateForm();
        });

        idSortButton.addActionListener(e -> {
            Collections.sort(model.getRows(), new Comparator<BookEntity>() {
                @Override
                public int compare(BookEntity o1, BookEntity o2) {
                    if(idSort) {
                        return Integer.compare(o2.getId(), o1.getId());
                    } else {
                        return Integer.compare(o1.getId(), o2.getId());
                    }
                }
            });
            model.fireTableDataChanged();
            idSort = !idSort;
            dateSort = false;
        });

        Predicate

        dateSortButton.addActionListener(e -> {
            Collections.sort(model.getRows(), new Comparator<BookEntity>() {
                @Override
                public int compare(BookEntity o1, BookEntity o2) {
                    if(dateSort) {
                        return o2.getWriteDateTime().compareTo(o1.getWriteDateTime());
                    } else {
                        return o1.getWriteDateTime().compareTo(o2.getWriteDateTime());
                    }
                }
            });
            model.fireTableDataChanged();
            dateSort = !dateSort;
            idSort = false;
        });

        clearFiltersButton.addActionListener(e -> {
            authorSortBox.setSelectedIndex(0);
            pageSortBox.setSelectedIndex(0);
        });
    }
}
