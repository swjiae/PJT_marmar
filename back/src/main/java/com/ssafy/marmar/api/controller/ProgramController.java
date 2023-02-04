package com.ssafy.marmar.api.controller;

import com.ssafy.marmar.api.request.SelectGamePostReq;
import com.ssafy.marmar.api.request.StudentRegisterPostReq;
import com.ssafy.marmar.api.response.PictureRes;
import com.ssafy.marmar.api.response.StudentRes;
import com.ssafy.marmar.api.response.WatchRes;
import com.ssafy.marmar.api.response.WordRes;
import com.ssafy.marmar.api.service.ProgramService;
import com.ssafy.marmar.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/program")
public class ProgramController {

    @Autowired
    ProgramService programService;

    // word 연습하기
    @GetMapping("/word/{difficulty}")
    public ResponseEntity<List<WordRes>> wordSpeakingList(@PathVariable String difficulty){
        List<WordRes> list = programService.wordlist(difficulty);
        return ResponseEntity.status(200).body(list);
    }

    // word 게임하기
    @GetMapping("/word/game")
    public ResponseEntity<List<WordRes>> wordGameList(){
        List<WordRes> list = programService.wordgamelist();
        return ResponseEntity.status(200).body(list);
    }

    // clock 연습하기
    @GetMapping("/clock/{difficulty}")
    public ResponseEntity<List<WatchRes>> watchList(@PathVariable String difficulty){
        List<WatchRes> list = programService.watchlist(difficulty);
        return ResponseEntity.status(200).body(list);
    }

    // clock 게임하기
    @GetMapping("/clock/game")
    public ResponseEntity<List<WatchRes>> watchGameList(){
        List<WatchRes> list = programService.watchgamelist();
        return ResponseEntity.status(200).body(list);
    }

    // picture 연습하기
    @GetMapping("/picture/{difficulty}")
    public ResponseEntity<List<PictureRes>> pictureList(@PathVariable String difficulty){
        List<PictureRes> list = programService.picturelist(difficulty);
        return ResponseEntity.status(200).body(list);
    }

    // picture 게임하기
    @GetMapping("/picture/game")
    public ResponseEntity<List<PictureRes>> pictureGameList(){
        List<PictureRes> list = programService.picturegamelist();
        return ResponseEntity.status(200).body(list);
    }

    // 수업 중 프로그램
    @PostMapping("/select/game")
    public ResponseEntity<Object> register(@RequestBody SelectGamePostReq selectGamePostReq){

        if(selectGamePostReq.getGame().equals("word")){
            List<WordRes> list = programService.selectWordGameList(selectGamePostReq.getDifficulty(), selectGamePostReq.getNum());
            return ResponseEntity.status(200).body(list);
        } else if(selectGamePostReq.getGame().equals("clock")){
            List<WatchRes> list = programService.selectWatchGameList(selectGamePostReq.getDifficulty(), selectGamePostReq.getNum());
            return ResponseEntity.status(200).body(list);
        } else if(selectGamePostReq.getGame().equals("picture")){
            List<PictureRes> list = programService.selectPictureGameList(selectGamePostReq.getDifficulty(), selectGamePostReq.getNum());
            return ResponseEntity.status(200).body(list);
        }
        return ResponseEntity.status(200).body(null);
    }
    
}
