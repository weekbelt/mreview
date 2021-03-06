package me.weekbelt.mreview.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {

    private Long mno;

    private String title;

    private List<MovieImageDTO> imageDTOList = new ArrayList<>();

    private double age;

    private int reviewCnt;

    private LocalDateTime regDate;

    private LocalDateTime modDate;
}
