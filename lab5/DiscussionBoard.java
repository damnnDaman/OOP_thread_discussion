package lab5;

/**

 * Name--> Daman Kumar
 * Student ID --> 1306900
 * Compile --> javac lab4/*.java

 * Run --> java lab4/*.java

 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DiscussionBoard extends JFrame implements ActionListener {
    public JComboBox<String> comboBox;
    public ArrayList<Post> posts = new ArrayList<>();
    public ArrayList<User> UserList = new ArrayList<>();
    public HashMap<String, ArrayList<Integer>> userPosts = new HashMap<>();
    public JPanel OptionBar = new JPanel();
    public JFrame frame = new JFrame("Discussion Board");
    public JPanel BoardArea = new JPanel();
    public JTextArea messageArea = new JTextArea();
    private int postIdCounter = 0;
    /**
     * Main method to create an instance of DiscussionBoard
     *
     * @param args
     */
    public static void main(String[] args) {
        DiscussionBoard discussionBoard = new DiscussionBoard();
    }

    public DiscussionBoard() {
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
frame.setResizable(false);

        OptionBar.setLayout(new FlowLayout());
        String[] menus = {"Register User", "Create Text Post", "Search by Username"};
        comboBox = new JComboBox<>(menus);
        comboBox.addActionListener(this);

        OptionBar.add(comboBox);

        BoardArea.setLayout(new BorderLayout());
        BoardArea.add(new JScrollPane(messageArea), BorderLayout.EAST);


        frame.add(OptionBar, BorderLayout.NORTH);
        frame.add(BoardArea, BorderLayout.CENTER);
        frame.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == comboBox) {
            String selected = (String) comboBox.getSelectedItem();
            System.out.println("Selected: " + selected);

            switch (selected) {
                case "Register User":
                    openRegisterUserPanel();

                    break;
                case "Create Text Post":
                    openCreateTextPostPanel();
                    break;
                case "Search by Username":
                    openSearchByUsernamePanel();
                    break;
                default:
                    System.out.println("Invalid selection");
                    break;
            }
        }
    }

    private void openRegisterUserPanel() {

        JPanel registerPanel = new JPanel(new GridLayout(1,2, 0, 0));

        JPanel userCredents = new JPanel(new GridLayout(0,1));
        JPanel messagePanel = new JPanel(new BorderLayout());

        JLabel fullNameLabel = new JLabel("Full Name:");
        JTextField fullNameField = new JTextField();
//        fullNameField.setPreferredSize(new Dimension(100, 20));

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
//        usernameField.setPreferredSize(new Dimension(100, 20));
        JButton registerButton = new JButton("Register");

        JLabel messageLabel = new JLabel("Message:");
        messagePanel.add(messageLabel, BorderLayout.NORTH);
        messageArea.setEditable(false);


        userCredents.add(fullNameLabel);
        userCredents.add(fullNameField);
        userCredents.add(usernameLabel);
        userCredents.add(usernameField);
        userCredents.add(new JLabel()); // Empty cell
        userCredents.add(registerButton);
        registerPanel.add(userCredents);

        messagePanel.add(messageArea, BorderLayout.CENTER);
        registerPanel.add(messagePanel);



        BoardArea.removeAll();
        BoardArea.add(registerPanel, BorderLayout.CENTER);
        BoardArea.revalidate();
        BoardArea.repaint();

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fullName = fullNameField.getText().trim();
                String username = usernameField.getText().trim().toLowerCase();

                if (fullName.isEmpty() || username.isEmpty()) {
                    messageArea.setText("Full Name and Username cannot be empty.");
                    return;
                }

                for (User current_user : UserList) {
                    if (current_user.getUserName().equals(username)) {
                        messageArea.setText("This username already exists\n\n**REQUEST DENIED**\nPick a different username");
                        return;
                    }
                }

                try {
                    User user = new User(fullName, username);
                    UserList.add(user);
                    userPosts.put(username, new ArrayList<>());

                    messageArea.setText("User created: " + user.getFullName() + " (@" + user.getUserName() + ")");
                } catch (IllegalArgumentException ex) {
                    messageArea.setText(ex.getMessage());
                }
            }
        });
    }

    private void openCreateTextPostPanel() {
        JPanel createPostPanel = new JPanel(new GridLayout(1, 2, 0, 0));

        JPanel postDetails = new JPanel(new GridLayout(0, 1));
        JPanel messagePanel = new JPanel(new BorderLayout());

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        JButton nextButton = new JButton("Next");

        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField();

        JLabel contentLabel = new JLabel("Content:");
        JTextArea contentArea = new JTextArea();
        JButton postButton = new JButton("Post");

        JLabel messageLabel = new JLabel("Message:");
        messagePanel.add(messageLabel, BorderLayout.NORTH);
        messageArea.setEditable(false);

        postDetails.add(usernameLabel);
        postDetails.add(usernameField);
        postDetails.add(new JLabel()); // Empty cell
        postDetails.add(nextButton);
        createPostPanel.add(postDetails);

        messagePanel.add(messageArea, BorderLayout.CENTER);
        createPostPanel.add(messagePanel);

        BoardArea.removeAll();
        BoardArea.add(createPostPanel, BorderLayout.CENTER);
        BoardArea.revalidate();
        BoardArea.repaint();

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim().toLowerCase();
                boolean registered = false;
                User userW = null;
                for ( User current_user : UserList) {
                    if (current_user.getUserName().equals(username)) {
                        registered = true;
                        userW = current_user;
                        break;
                    }
                }

                if (registered) {
                    postDetails.removeAll();
                    postDetails.add(titleLabel);
                    postDetails.add(titleField);
                    postDetails.add(contentLabel);
                    postDetails.add(new JScrollPane(contentArea));
                    postDetails.add(new JLabel()); // Empty cell
                    postDetails.add(postButton);
                    postDetails.revalidate();
                    postDetails.repaint();

                    postButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String title = titleField.getText().trim();
                            String content = contentArea.getText().trim();

                            if (title.isEmpty() || content.isEmpty()) {
                                messageArea.setText("Title and Content cannot be empty.");
                                return;
                            }

                            try {
                                User user = null;
                                for (User current_user : UserList) {
                                    if (current_user.getUserName().equals(username)) {
                                        user = current_user;
                                        break;
                                    }
                                }

                                if (user != null) {
                                    Post post = new TextPost(++postIdCounter, title, content, user);
                                    posts.add(post);
                                    userPosts.putIfAbsent(username, new ArrayList<>());
                                    userPosts.get(username).add(post.getPostId());

                                    messageArea.setText("Post created successfully.");
                                } else {
                                    messageArea.setText("User not found.");
                                }
                            } catch (IllegalArgumentException ex) {
                                messageArea.setText(ex.getMessage());
                            }
                        }
                    });
                } else {
                    messageArea.setText("**Permission Denied**\nYou need to register first.");
                }
            }
        });
    }

    private void openSearchByUsernamePanel() {
        JPanel searchPanel = new JPanel(new GridLayout(1, 2, 0, 0));

        JPanel searchDetails = new JPanel(new GridLayout(0, 1));
        JPanel messagePanel = new JPanel(new BorderLayout());

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        JButton searchButton = new JButton("Search");

        JLabel messageLabel = new JLabel("Message:");
        messagePanel.add(messageLabel, BorderLayout.NORTH);
        messageArea.setEditable(false);

        searchDetails.add(usernameLabel);
        searchDetails.add(usernameField);
        searchDetails.add(new JLabel()); // Empty cell
        searchDetails.add(searchButton);
        searchPanel.add(searchDetails);

        messagePanel.add(messageArea, BorderLayout.CENTER);
        searchPanel.add(messagePanel);

        BoardArea.removeAll();
        BoardArea.add(searchPanel, BorderLayout.CENTER);
        BoardArea.revalidate();
        BoardArea.repaint();

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim().toLowerCase();
                ArrayList<Integer> indices = userPosts.get(username);

                if (indices == null || indices.isEmpty()) {
                    messageArea.setText("No posts found for user @" + username);
                } else {
                    StringBuilder result = new StringBuilder();
                    for (int index : indices) {
                        Post post = posts.get(index - 1);
                        result.append(post.toString());
                    }
                    messageArea.setText(result.toString());
                }
            }
        });
    }

}