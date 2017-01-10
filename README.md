# ScrollDemo
What's the difference between a method and scrollBy scrollTo methods described

#不积跬步无以至千里
View的移动

- mScrollX：表示离视图起始位置的x水平方向的偏移量
- mScrollY：表示离视图起始位置的y垂直方向的偏移量

分别通过getScrollX() 和getScrollY()方法获得。
注意：mScrollX和mScrollY指的并不是坐标，而是偏移量。

-
- scrollTo()
	是将View中的内容滑动到相应的位置，参考的坐标系远点为parent View的左上角
- scrollBy()
	其实是对scrollTo方法的包装，移动的是相对位置

		public void scrollTo(int x, int y) { 
   			if (mScrollX != x || mScrollY != y) {  
        		int oldX = mScrollX;  
        		int oldY = mScrollY;  
        		mScrollX = x;  
       			mScrollY = y;  
       		 	invalidateParentCaches();  
        		onScrollChanged(mScrollX, mScrollY, oldX, oldY);  
        		if (!awakenScrollBars()) {  
            		postInvalidateOnAnimation();  
        		}  
    		}  
		}  


		public void scrollBy(int x, int y) { 
			scrollTo(mScrollX + x, mScrollY + y); 
		}  
	
- getScrollX():获取当前的mScrollX值。
- getScrollY()：获取当前的mScrollY值。

需要注意的是：注意：调用View的scrollTo()和scrollBy()是用于滑动View中的内容，而不是把某个View的位置进行改变。如果想改变莫个View在屏幕中的位置，可以使用如下的方法。
调用public void offsetLeftAndRight(int offset)用于左右移动方法或public void offsetTopAndBottom(int offset)用于上下移动。
如：button.offsetLeftAndRignt(300)表示将button控件向左移动300个像素。