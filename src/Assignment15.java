import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
public class Assignment15 extends Application {
    private static final int BOARD_SIZE = 5;
    private static final int WINNING_COUNT = 5;
    private Button[][] buttons;
    private char currentPlayer = 'X';
    private boolean gameOver = false;
    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        buttons = new Button[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                Button button = new Button();
                button.setPrefSize(100, 100);
                final int row = i;
                final int col = j;  // Create final or effectively final copies of i and j
                button.setOnAction(event -> {
                    if (!gameOver && buttons[row][col].getText().isEmpty()) {
                        buttons[row][col].setText(Character.toString(currentPlayer));
                        if (checkForWin(row, col)) {
                            gameOver = true;
                            showWinner(currentPlayer);
                        } else if (checkForDraw()) {
                            gameOver = true;
                            showDraw();
                        } else {
                            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                        }
                    }
                });
                buttons[i][j] = button;
                gridPane.add(button, j, i);
            }
        }
        Scene scene = new Scene(gridPane, 600, 600);
        primaryStage.setTitle("Modified Tic Tac Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private boolean checkForWin(int row, int col) {
        int count = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (buttons[row][i].getText().equals(Character.toString(currentPlayer))) {
                count++;
            } else {
                count = 0;
            }
            if (count == WINNING_COUNT) return true;
        }
        count = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (buttons[i][col].getText().equals(Character.toString(currentPlayer))) {
                count++;
            } else {
                count = 0;
            }
            if (count == WINNING_COUNT) return true;
        }
        count = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            int r = row - col + i;
            int c = i;
            if (r >= 0 && r < BOARD_SIZE && c >= 0 && c < BOARD_SIZE) {
                if (buttons[r][c].getText().equals(Character.toString(currentPlayer))) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == WINNING_COUNT) return true;
            }
        }
        count = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            int r = row + col - i;
            int c = i;
            if (r >= 0 && r < BOARD_SIZE && c >= 0 && c < BOARD_SIZE) {
                if (buttons[r][c].getText().equals(Character.toString(currentPlayer))) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == WINNING_COUNT) return true;
            }
        }
        return false;
    }
    private boolean checkForDraw() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (buttons[i][j].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }
    private void showWinner(char player) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText("Player " + player + " wins!");
        alert.showAndWait();
    }
    private void showDraw() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText("It's a draw!");
        alert.showAndWait();
    }
    public static void main(String[] args) {
        launch(args);
    }
}