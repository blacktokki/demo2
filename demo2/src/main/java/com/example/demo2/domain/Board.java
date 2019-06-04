package com.example.demo2.domain;


import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="table1")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    private Integer idx;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="content", nullable = false)
    private String content;
    
    @Column(name="name", nullable = false)
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="reg_date", nullable = false)
    private Date regDate= new Date();

    @Column(name="category", nullable = false)
    private String category=new String();
    
    @Column(name="comp_name",nullable = false)
    private String compName;
    
    @Column(name="hit", nullable = false)
    //@ColumnDefault()
    private Integer hit=0;
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}
    
    public Integer getIdx() {
		return idx;
	}

	public void setIdx(Integer idx) {
		this.idx = idx;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getHit() {
		return hit;
	}

	public void setHit(Integer hit) {
		this.hit = hit;
	}
}
