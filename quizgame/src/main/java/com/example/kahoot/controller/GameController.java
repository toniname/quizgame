package com.example.kahoot.controller;

import com.example.kahoot.dto.ScoreRequest;
import com.example.kahoot.model.Quiz;
import com.example.kahoot.model.User;
import com.example.kahoot.service.GameService;
import com.example.kahoot.service.QuizService;
import com.example.kahoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

// GameController.java
@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private UserService userService;

    @Autowired
    private QuizService quizService;

    @PostMapping("/calculate-score")
    public ResponseEntity<Integer> calculateScore(@RequestBody ScoreRequest request) {
        // Валидация входных данных
        if (request.getUserId() == null || request.getQuizId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User  ID and Quiz ID must be provided");
        }

        // Получаем объекты User и Quiz по идентификаторам
        User user = userService.getUserById(request.getUserId());
        Quiz quiz = quizService.getQuizById(request.getQuizId());

        // Вычисляем результат
        int score = gameService.calculateScore(user, quiz);
        return ResponseEntity.ok(score);
    }
}
