package com.boot3.myrestapi.lectures;

import com.boot3.myrestapi.lectures.dto.LectureReqDto;
import com.boot3.myrestapi.lectures.validator.LectureValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping(value = "/api/lectures", produces = MediaTypes.HAL_JSON_VALUE)
@RequiredArgsConstructor
public class LectureController {

    private final LectureRepository lectureRepository;
    private final ModelMapper modelMapper;
    private final LectureValidator lectureValidator;

    //constructor injection
//    public LectureController(LectureRepository lectureRepository) {
//        this.lectureRepository = lectureRepository;
//    }

    @PostMapping
    public ResponseEntity<?> createLecture(@RequestBody @Valid LectureReqDto lectureReqDto, Errors errors) {
        if(errors.hasErrors()) {
            return getErrors(errors);
        }

        //biz 로직에 대한 입력항목 체크
        lectureValidator.validate(lectureReqDto, errors);
        if(errors.hasErrors()) {
            return getErrors(errors);
        }

        Lecture lecture = modelMapper.map(lectureReqDto, Lecture.class);
        //free, offline 필드 초기화
        lecture.update();
        Lecture addedLecture = lectureRepository.save(lecture);

        // api/lectures/10 링크를 생성
        WebMvcLinkBuilder selfLinkBuilder =
                WebMvcLinkBuilder.linkTo(LectureController.class).slash(addedLecture.getId());
        URI createUri = selfLinkBuilder.toUri();
        return ResponseEntity.created(createUri).body(addedLecture);
    }

    private static ResponseEntity<Errors> getErrors(Errors errors) {
        return ResponseEntity.badRequest().body(errors);
    }
}