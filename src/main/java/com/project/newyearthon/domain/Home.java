package com.project.newyearthon.domain;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Home {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID")
    private User user;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String imgData; // JSON 형태로 저장

    @Transient
    private List<String> imgList; // 실제 사용하는 리스트 (직렬화/역직렬화)


    public void setimgData(String imgData) throws Exception {
        this.imgData = imgData;

        // JSON -> List 역직렬화
        ObjectMapper mapper = new ObjectMapper();
        this.imgList = mapper.readValue(imgData, new TypeReference<List<String>>() {});
    }

    public void setImgList(List<String> imgList) throws Exception {
        this.imgList = imgList;

        // List -> JSON 직렬화
        ObjectMapper mapper = new ObjectMapper();
        this.imgData = mapper.writeValueAsString(imgList);
    }

    private int contactPeriod;
    private int deposit;
    private int monthlyRent;
    private String address;
    private String roomType;
    private String roomInfo;
    private String allowance;
    private String mealInfo;
    private String sleepInfo;
    private String oneLineInfo;
    private String detailInfo;
}
