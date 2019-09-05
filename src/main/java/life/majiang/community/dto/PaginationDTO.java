package life.majiang.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private Boolean showPrevious;
    private Boolean showFirstPage;
    private Boolean showNext;
    private Boolean showEndPage;
    private Integer page;//当前页
    private Integer totalPage;
    private List<Integer> pages=new ArrayList<>();//显示页数集合

    public void setPagination(Integer totalPage,Integer page) {
        this.page=page;
        this.totalPage=totalPage;
        pages.add(page);
        for(int i=1;i<=3;i++){
            if (page-i>0){
                pages.add(0,page-i);
            }
            if (page+i<=totalPage){
                pages.add(page+i);
            }
        }
        showPrevious=page==1?false:true;

        showNext=page==totalPage?false:true;
        showFirstPage=pages.contains(1)?false:true;

        showEndPage=pages.contains(totalPage)?false:true;
    }
}
