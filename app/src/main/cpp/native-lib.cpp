#include <jni.h>
#include <string>
#include "calculate.h"
using namespace std;


//jstring
//Java_com_example_sandeep_calculatorndkjava_MainActivity_stringFromJNI(
//        JNIEnv* env,
//        jobject obj) {
//    jstring res= "hello";
//    return (*env)->NewStringUTF(env,res);
//}
extern "C" JNIEXPORT void JNICALL
Java_com_example_sandeep_calculatorndkjava_MainActivity_ToTextView(JNIEnv *env,
                       jobject thiz,
                       jstring expression,
                       jobject tv) {
    string res="";
    try {
        string exp=env->GetStringUTFChars(expression,JNI_FALSE);
        res=to_string(calculetorExp(exp));
    } catch (const invalid_argument &e) {
        res=e.what();
    } catch (const exception &e){
        using namespace string_literals;
        res="internal error:"s+e.what()+"exception type"+typeid(e).name();
    } catch(...){
        res="internal error";
    }


    jclass ev_class=env->GetObjectClass(tv);

    if (ev_class == nullptr){
        return;
    }

    auto set_text_method_id = env->GetMethodID(ev_class, "setText", "(Ljava/lang/CharSequence;)V");
    if (set_text_method_id == nullptr) {
        return; //如果方法ID没有找到
    }

    env->CallVoidMethod(tv, set_text_method_id, env->NewStringUTF(res.c_str()));

}