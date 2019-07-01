package com.example.tictactoe

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var player1 = ArrayList<Int>();
    var player2 = ArrayList<Int>();
    var emptyCells = ArrayList<Int>()

    var activePlayer = 1;
    var winner = 0;
    var gameOver = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (i in 1..9) {
            emptyCells.add(i);
        }
    }

    //player 1 plays  == Is Winner ? isGameOver ?
    //player 2 plays == Is Winner ? Is Game over?

//function buttonClicked means player 1 is playing and he clicked a button
    // 1. change the color of the button
    // 2. disable that button
    // 3. add that button to the player 1 score
    // 4. check if the player 1 is the winner ?
    // 5. If player 1 is winner (display so and show refresh button disable all the buttons
    // 6. If player 1 is not the winner and game is not over call auto play
    // 7. If player 1 is not winner but the game is over then disable all buttons and show Refresh button

    fun buttonClicked(view: View) {
        val selectedButton = view as Button
        var cellId = 0;
        when (selectedButton.id) {
            R.id.button1 -> cellId = 1
            R.id.button2 -> cellId = 2
            R.id.button3 -> cellId = 3
            R.id.button4 -> cellId = 4
            R.id.button5 -> cellId = 5
            R.id.button6 -> cellId = 6
            R.id.button7 -> cellId = 7
            R.id.button8 -> cellId = 8
            R.id.button9 -> cellId = 9
        }

        playGame(cellId, selectedButton)

//        if (gameOver != 1){
//            playGame(cellId, selectedButton)
//
//        }
//        else {
//            refresh.visibility= View.VISIBLE
//            refresh.text = "Refresh"
//        }
    }

    fun playGame(cellId: Int, selectedButton: Button) {
        if (activePlayer == 1) {
            activePlayer = 2
            selectedButton.setBackgroundColor(Color.CYAN)
            player1.add(cellId)
            emptyCells.removeAt(emptyCells.indexOf(cellId))
            selectWinner()
            if (winner == 0 && !gameOver) {
                autoPlay()
            } else if (winner == 0 && gameOver) {
                /// show refresh button
                showRefresh()
            } else if (winner > 0)
                showRefresh()
            //show refresh button
        }
        selectedButton.isEnabled = false
    }


    fun selectWinner() {

        if (player1.contains(1) && player1.contains(2) && player1.contains(3)) {
            winner = 1;
        }
        if (player1.contains(1) && player1.contains(4) && player1.contains(7)) {
            winner = 1;
        }
        if (player1.contains(1) && player1.contains(5) && player1.contains(9)) {
            winner = 1;
        }
        if (player1.contains(3) && player1.contains(5) && player1.contains(7)) {
            winner = 1;
        }
        if (player1.contains(2) && player1.contains(5) && player1.contains(8)) {
            winner = 1;
        }
        if (player1.contains(4) && player1.contains(5) && player1.contains(6)) {
            winner = 1;
        }
        if (player1.contains(7) && player1.contains(8) && player1.contains(9)) {
            winner = 1;
        }
        if (player1.contains(3) && player1.contains(6) && player1.contains(9)) {
            winner = 1;
        }

        if (player2.contains(1) && player2.contains(2) && player2.contains(3)) {
            winner = 2;
        }
        if (player2.contains(1) && player2.contains(4) && player2.contains(7)) {
            winner = 2;
        }
        if (player2.contains(2) && player2.contains(5) && player2.contains(8)) {
            winner = 2;
        }
        if (player2.contains(1) && player2.contains(5) && player2.contains(9)) {
            winner = 2;
        }
        if (player2.contains(4) && player2.contains(5) && player2.contains(6)) {
            winner = 2;
        }
        if (player2.contains(3) && player2.contains(5) && player2.contains(7)) {
            winner = 2;
        }
        if (player2.contains(7) && player2.contains(8) && player2.contains(9)) {
            winner = 2;
        }
        if (player2.contains(3) && player2.contains(6) && player2.contains(9)) {
            winner = 2;
        }

        if (winner > 0) {
            Toast.makeText(this, "Player $winner won the game", Toast.LENGTH_LONG).show()
            gameOver = true
        }
    }

    fun showRefresh() {
        refresh.setVisibility(View.VISIBLE);
        refresh.setText("Refresh");

    }

    fun initGame() {
        recreate();
        emptyCells.removeAll(emptyCells);
        player1.removeAll(player1)
        player2.removeAll(player2)
        activePlayer = 1;
        winner = 0;
        gameOver = false;
        System.out.println(emptyCells.size);
        System.out.println(player1.size);

        for (i in 1..9) {
            emptyCells.add(i);
        }

    }

    override fun recreate() {
        if (android.os.Build.VERSION.SDK_INT >= 11) {
            super.recreate()
        } else {
            startActivity(intent)
            finish()
        }
    }

    fun refreshGame(view: View) {
        //val refreshButton = view as Button
        //refresh.setVisibility(View.INVISIBLE);
        initGame()
    }


    // 1. click the button change the color of the button
    // 2. disable that button
    // 3. add that button to the player 1 score
    // 4. check if the player 1 is the winner ?
    // 5. If player 1 is winner (display so and show refresh button disable all the buttons
    // 6. If player 1 is not the winner and game is not over call auto play
    // 7. If player 1 is not winner but the game is over then disable all buttons and show Refresh button

    fun autoPlay() {
        if (emptyCells.size > 0) {
            var randomCell = Random.nextInt(0, emptyCells.size - 1)
            var cellId = emptyCells[randomCell]


            var selectedButton: Button
            when (cellId) {
                1 -> selectedButton = button1
                2 -> selectedButton = button2
                3 -> selectedButton = button3
                4 -> selectedButton = button4
                5 -> selectedButton = button5
                6 -> selectedButton = button6
                7 -> selectedButton = button7
                8 -> selectedButton = button8
                9 -> selectedButton = button9
                else -> {
                    selectedButton = button1
                }
            }

            activePlayer = 1;
            selectedButton.setBackgroundColor(Color.GREEN)
            selectedButton.isEnabled = false

            player2.add(cellId)
            emptyCells.removeAt(emptyCells.indexOf(cellId))
            selectWinner()
            if (winner == 0 && !gameOver) {
                //autoPlay()
            } else if (winner == 0 && gameOver) {
                /// show refresh button
                showRefresh()
            } else if (winner > 0)
            //show refresh button
                showRefresh()
        } else {
            gameOver = true;
            showRefresh();
            Toast.makeText(this, "Game Over! click Refresh", Toast.LENGTH_LONG).show()
        }
    }
}