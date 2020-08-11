package com.example.calculator

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //给按钮添加点击事件
        mNextBtn.setOnClickListener {
            //跳转到下一个页面进行计算
            //创建Intent
            Intent().apply {
                //设置值
                putExtra("first",mFirst.text.toString().toInt())
                putExtra("second",mSecond.text.toString().toInt())

                //设置从哪个页面跳转到哪个页面
                setClass(this@MainActivity,DetailActivity::class.java)

                //跳转
                startActivityForResult(this,123)
            }
        }

        //分享按钮事件
        mShareBtn.setOnClickListener {
            //使用隐式跳转到分享App的某个页面
            Intent().apply {
                action = "swl.WeChat"
                data = Uri.parse("content: 今天的晚饭非常美味！！")
            }.also {
                startActivityForResult(it,456)
            }
        }
    }
     //接收回调数据
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
         if(requestCode==123){
             data?.getIntExtra("result",0).also {
                 //将结果赋值到textView上
                 mResult.text = it.toString()
             }
         }else if(requestCode==456){
             //分享页面返回回来的结果
            data?.getStringExtra("ShareResult").also {
                //弹出一个吐司，提示框
                Toast.makeText(this,it,Toast.LENGTH_LONG).show()
            }
         }
    }
}
