package com.atguigu.chapter08.work1

/**
 * @author tianmin
 * @date 2020/2/20 0020
 * @notes
 */
class Children(inName:String) {
  var name:String = inName
}

object Children{
  var totalChildrenNum:Int = 0;

  def joinChildrenGame(children: Children): Unit ={
    printf("%S 加入了游戏.\n",children.name)
    totalChildrenNum += 1
  }

  def showTotal(): Unit ={
    printf("总共有%d个小孩在玩游戏\n",totalChildrenNum)
  }
}
