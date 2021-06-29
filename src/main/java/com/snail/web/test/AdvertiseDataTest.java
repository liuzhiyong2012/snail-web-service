package com.snail.web.test;

//@Component
public class AdvertiseDataTest {
    public static void main(String[] args){

    }

/**
    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    AdvertiseMapper advertiseMapper;

    @Autowired
    AdvertisePositionMapper advertisePositionMapper;

    public static void main(String[] args){
        AdvertiseDataTest advertiseDataTest = new AdvertiseDataTest();
        advertiseDataTest.createData();
    }

    public  void createData(){
        AdvertisePosition advertisePositionQuery = new AdvertisePosition();
        advertisePositionQuery.setPageNumber(1);
        advertisePositionQuery.setPageSize(10000);
        List<AdvertisePosition> advertisePositionList = advertisePositionMapper.page(advertisePositionQuery);

        Article articleQuery = new Article();
        articleQuery.setPageNumber(1);
        articleQuery.setPageSize(1000);
        List<Article> articleList = articleMapper.page(articleQuery);

        advertiseMapper.delAll();
        for(AdvertisePosition advertisePosition:advertisePositionList){
            int column  =  Integer.parseInt(advertisePosition.getColumn());
            int  row  =   Integer.parseInt(advertisePosition.getColumn());

            for(int i = 0;i < row;i++){
                for(int j = 0;j < row;j++){
                    Random random = new Random();
                    int randomNumber = random.nextInt();
                    Article article = articleList.get(randomNumber);

                    Advertise advertise = new Advertise();
                    advertise.setId(IdWorker.getId());
                    advertise.setRow(i + "");
                    advertise.setRow(j + "");
                    advertise.setName(advertisePosition.getName() +  "_" +  (i + 1) + "" + (j + 1));
                    advertise.setStartTime(new Date());
                    advertise.setEndTime(new Date());
                    advertise.setPositionId(advertisePosition.getId());
                    advertise.setArticleId(article.getId());
                    advertise.setContent(null);
                    advertise.setLinkUrl(null);
                    advertise.setType("2");
                    advertise.setCreatedBy(UserConstants.ADMIN_USER_ID);
                    advertise.setCreatedTime(new Date());
                    advertise.setUpdatedBy(UserConstants.ADMIN_USER_ID);
                    advertise.setUpdatedTime(new Date());
                    advertise.setStatus(DtoConstants.STATUS_NORMAL);
                    advertise.setIsDeleted(DtoConstants.IS_DELETE_NO);
                    advertise.setImageUrl(article.getImageUrl());



                }
            }

        }
    }
**/
}
