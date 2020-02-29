package com.atguigu.chapter12.work4

/**
 * @author tianmin
 * @date 2020/2/22 0022
 * @notes
 */
case class Bundle(description:String,discount:Double,item:Item*) extends Item{

}
