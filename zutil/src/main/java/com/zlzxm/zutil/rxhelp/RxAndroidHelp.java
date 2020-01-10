package com.zlzxm.zutil.rxhelp;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.zlzxm.zutil.ui.widget.ztipview.ZTipView;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zlz
 * on  2019-09-11
 */
public class RxAndroidHelp {


    public static <T> ObservableTransformer<T,T> turn(){

        return turn(null,null);
    }

    public static <T> ObservableTransformer<T,T> turn(DialogFragment dialogFragment,FragmentManager fragmentManager){



        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe( disposable -> {
                    if(dialogFragment != null && fragmentManager!=null)
                        dialogFragment.show(fragmentManager,"loading");
                })
                .doOnNext( t -> {

                    if(dialogFragment!=null ){
                        dialogFragment.dismissAllowingStateLoss();
                    }

                })
                .doOnDispose(() -> {
                    if(dialogFragment!=null ){
                        dialogFragment.dismissAllowingStateLoss();
                    }
                })
                .doOnError((Consumer) o -> {
                    if(dialogFragment!=null ){
                        dialogFragment.dismissAllowingStateLoss();
                    }
                })
                .doOnComplete( () -> {
                    if(dialogFragment!=null ){
                        dialogFragment.dismissAllowingStateLoss();
                    }
                });

    }



    public static <T> ObservableTransformer<T,T> turn(ZTipView zTipView){



        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> zTipView.loadingModel())
                .doOnNext(roomInfoRp -> zTipView.disMiss())
                .doOnDispose(() -> zTipView.disMiss())
                .doOnError(throwable -> zTipView.tipModel("出错了"));
    }

//       public static <T> ObservableTransformer<T,T> baseCallBack(){
//
//
//            return  new ObservableTransformer<T, T>() {
//                @Override
//                public ObservableSource<T> apply(Observable<T> upstream) {
//                    return upstream.flatMap(new Function<T, ObservableSource<?>>() {
//                        @Override
//                        public ObservableSource<?> apply(T t) throws Exception {
//                            return null;
//                        }
//                    });
//                }
//            }
//       }

}
