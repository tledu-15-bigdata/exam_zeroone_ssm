package com.tledu.cn.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tledu.cn.dao.QuestionBankDao;


import com.tledu.cn.pojo.QuestionBank;
import com.tledu.cn.service.QuestionBankService;
import com.tledu.cn.util.JDK8DateUtil;
import com.tledu.cn.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class QuestionBankServiceImpl implements QuestionBankService {
    //引入dao层
    @Autowired
    private QuestionBankDao questionBankDao;


    @Override
    public PageUtils findAllQuestionBank(Map<String, Object> params) {
        List<QuestionBank> allQuestionBank = questionBankDao.findAllQuestionBank(params.get("u_id").toString());
        //分页核心代码
        PageHelper.offsetPage(Integer.parseInt(params.get("offset").toString()),Integer.parseInt(params.get("pageNumber").toString()));
        PageInfo<QuestionBank> pageInfo=new PageInfo<>(allQuestionBank);
        return new PageUtils(pageInfo.getList(),new Long(pageInfo.getTotal()).intValue());
    }

    @Override
    public PageUtils findAllQuestionBank1(Map<String, Object> params) {
        List<QuestionBank> allQuestionBank = questionBankDao.findAllQuestionBank1(params.get("u_id").toString());
     //   System.out.println(allQuestionBank);
        //分页核心代码
        PageHelper.offsetPage(Integer.parseInt(params.get("offset").toString()),Integer.parseInt(params.get("pageNumber").toString()));
        PageInfo<QuestionBank> pageInfo=new PageInfo<>(allQuestionBank);
        return new PageUtils(pageInfo.getList(),new Long(pageInfo.getTotal()).intValue());
    }

    @Override
    public boolean addSingleChoice(QuestionBank questionBank) {
        boolean result=false;
        questionBank.setQ_id(UUID.randomUUID().toString());
        questionBank.setQ_type("单选题");
        questionBank.setQ_isdelete(0);
        LocalDateTime now = LocalDateTime.now();//创建本地时间对象
        String localDateTimeString = JDK8DateUtil.LocalDateTime2String(now, "yyyy-MM-dd HH:mm:ss");
        questionBank.setQ_createTime(localDateTimeString);
        questionBank.setQ_isadd(0);
        int i = questionBankDao.addSingleChoice(questionBank);
        if(i>0){
            result=true;
        }
        return result;
    }
    @Override
    public boolean addBriefAnswer(QuestionBank questionBank) {
        boolean result=false;
        questionBank.setQ_id(UUID.randomUUID().toString());
        questionBank.setQ_type("简答题");
        questionBank.setQ_isdelete(0);
        LocalDateTime now = LocalDateTime.now();//创建本地时间对象
        String localDateTimeString = JDK8DateUtil.LocalDateTime2String(now, "yyyy-MM-dd HH:mm:ss");
        questionBank.setQ_createTime(localDateTimeString);
        questionBank.setQ_isadd(0);
        int i = questionBankDao.addBriefAnswer(questionBank);
        if(i>0){
            result=true;
        }
        return result;
    }

    @Override
    public boolean deleteQuestionBank(String q_id) {
        boolean result=false;

        int i = questionBankDao.deleteQuestionBank(q_id);
        if(i>0){
            result=true;
        }
        return result;

    }

    @Override
    public boolean updateSingleChoice(QuestionBank questionBank) {
        boolean result=false;

        int i=questionBankDao.updateSingleChoice(questionBank);
        if(i>0){
            result=true;
        }
        return result;
    }

    @Override
    public boolean updateBriefAnswer(QuestionBank questionBank) {
        boolean result=false;

        int i=questionBankDao.updateBriefAnswer(questionBank);
        if(i>0){
            result=true;
        }
        return result;
    }

    @Override
    public PageUtils findAllSingleChoice(Map<String,Object> params) {
        List<QuestionBank> questionBankList=questionBankDao.findAllSingleChoice(params.get("u_id").toString());
      //  System.out.println(questionBankList);
        //分页核心代码
        PageHelper.offsetPage(Integer.parseInt(params.get("offset").toString()),Integer.parseInt(params.get("pageNumber").toString()));
        PageInfo<QuestionBank> pageInfo=new PageInfo<>(questionBankList);
        return new PageUtils(pageInfo.getList(),new Long(pageInfo.getTotal()).intValue());


    }

    @Override
    public PageUtils findAllBriefAnswer(Map<String,Object> params) {
        List<QuestionBank> questionBankList=questionBankDao.findAllBriefAnswer(params.get("u_id").toString());
        //分页核心代码
        PageHelper.offsetPage(Integer.parseInt(params.get("offset").toString()),Integer.parseInt(params.get("pageNumber").toString()));
        PageInfo<QuestionBank> pageInfo=new PageInfo<>(questionBankList);
        return new PageUtils(pageInfo.getList(),new Long(pageInfo.getTotal()).intValue());

    }

    @Override
    public PageUtils fuzzySearch(Map<String, Object> params) {
        String q_content=params.get("q_content").toString();
        String u_id=params.get("u_id").toString();
        List<QuestionBank> questionBankList = questionBankDao.fuzzySearch(q_content,u_id);
       // System.out.println(questionBankList);
        //分页核心代码
        PageHelper.offsetPage(Integer.parseInt(params.get("offset").toString()),Integer.parseInt(params.get("pageNumber").toString()));
        PageInfo<QuestionBank> pageInfo=new PageInfo<>(questionBankList);
        return new PageUtils(pageInfo.getList(),new Long(pageInfo.getTotal()).intValue());
    }

    @Override
    public boolean batchDelete(List<String> idList) {
        boolean result=false;
        int i=questionBankDao.batchDelete(idList);

        if(i>0){
            result=true;
        }
        return result;
    }




}
