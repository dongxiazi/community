package life.majiang.community.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
@Data
public class Question {
    private Integer id;
    @NotBlank(message = "title 不能为空")
    private String title;
    @NotBlank(message ="问题补充不能为空")
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    @NotBlank(message ="标签补充不能为空")
    private String tag;

}
