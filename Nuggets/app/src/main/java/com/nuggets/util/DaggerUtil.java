package com.nuggets.util;

/**
 * Created by Administrator on 2016/11/17.
 */

public class DaggerUtil {

    /**
     * public class WuKong{
     *     @Inject
     *     JinGuBang jingubang;
     *     @Inject
     *     public Wukong(){
     *
     *     }
     *     public String useJingubiang(){
     *         retuen this.jingubang.use();
     *     }
     * }

     *
     * public class JinGuBang{
     *     @Inject
     *     public JinGuBang(){
     *
     *     }
     *     public String use(){
     *         return "user jin gu bang";
     *     }
     * }
     * */

    /**
     *
     * @Module
     * public class XiYouModule{
     *     @privides
     *     @Singleton
     *     WuKong provideWukong(){
     *         return new Wukong();
     *     }
     *     @Provides
     *     @Singleton
     *     JinGuBang provideJinGuBang(){
     *         return new JinGuBang();
     *     }
     * }
     * */
    /**
     *
     * @Component(modules = {XiYouModule.class})
     * @Singleton
     * public interface XiYouComponent{
     *     void inject(Wukong wk);
     *     void inject(Activity a);
     * }
     * */

    /**
     * publci class MainActivity extends AppCompatActivity{
     *     @Inject
     *     WuKong wukong;
     *     protected void onCreate(Bundle savedInstanceState){
     *         setcontentView(R.layout.main);
     *         TextView text = (TextView)findviewByid(R.id.tv_text);
     *
     *         XiYouComponent xiyou = Da.....
     *
     *         text.settext(wukong.useJinguBang());
     *     }
     * }
     * */


    /**888888888888888888888888888888888888*/
    /**
     * one:
     * @Module(includes = {ToasModule.class})
     * pulbic class AppModule{
     *     private App app;
     *     public AppModule(App app){
     *         this.app = app;
     *     }
     *     @Provides
     *     @Singleton
     *     public Application provideApplicationContext(){
     *         return app;
     *     }
     * }
     *
     * @Module(injects={App.class,MainActivity.class},complete =false,library =true)
     * public calss ToastModule{
     *     @Provides
     *     @Singleton
     *     public Toast provideToast(Application app){
     *         retuern toast.makeText(aoo,"",Toast.Leng..);
     *     }
     * }
     *
     * twe:
     * public class App extends Application{
     *     private Objuctgraph appg;
     *     @Injuct
     *     Toast toast;
     *
     *     public void onCreate(){
     *         ......
     *     }
     *     protected List<Object> getModules(){
     *         return Ayyay....;
     *     }
     * }
     *
     * */

}
