package com.ysd.iep.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

import com.ysd.iep.annotation.PermissionMethod;
import com.ysd.iep.annotation.PermissionType;
import com.ysd.iep.entity.Post;
import com.ysd.iep.entity.PostQuery;
import com.ysd.iep.entity.Reply;

@PermissionType("帖子")
public interface PostService {
	/**
	 * 带条件分页查询帖子
	 * 
	 * @param post
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Post> queryAllPage(PostQuery postQuery, Pageable pageable);

	/**
	 * 获取帖子详情
	 * 
	 * @param postId
	 * @param parentId
	 * @return
	 */
	public Reply getPostDetails(Integer postId, Integer parentId);

	/**
	 * 获取帖子点赞数
	 * 
	 * @param userId
	 * @param replyId
	 * @return
	 */
	public Integer getLikeNum(Integer replyId);

	/**
	 * 更新帖子点赞数
	 * 
	 * @param replyId
	 * @return
	 */
	public Integer updateLikeNum(Integer replyId, Integer likeNum);
	/**
	 *   获取帖子举报数
	 * @param replId
	 * @return
	 */
	public Integer getReportNum(Integer replyId);
	/**
	 * 更新帖子举报数
	 * 
	 * @param replyId
	 * @return
	 */
	public Integer updateReportNum(Integer replyId, Integer reportNum);

	/**
	 * 根据postid查询帖子
	 * 
	 * @param postId
	 * @return
	 */
	public Post queryPostByPostId(Integer postId);

	/**
	 * 发表帖子
	 * @param title
	 * @param content
	 * @param parentId
	 * @param postId
	 * @return
	 */
	public Integer publicPost(String title, String content, Integer parentId,String userId);
	/**
	 * 通过用户id分页查询帖子
	 * @param userId
	 * @param page
	 * @param rows
	 * @return
	 */
	public Page<Post> queryPostByUserId(String userId,Pageable pageable);
	
	/**
	 * 置顶帖子
	 * @param postId
	 * @return
	 */
	@PreAuthorize("hasAuthority('post:stickPost')")
    @PermissionMethod("置顶帖子")
	public Integer stickPost(Integer postId);
	/**
	 * 取消置顶
	 * @param postId
	 * @return
	 */
	@PreAuthorize("hasAuthority('post:cancelStick')")
    @PermissionMethod("取消帖子置顶")
	public Integer cancelStick(Integer postId);
	
	/**
	 * 通过帖子id获取帖子
	 * @param postId
	 * @return
	 */
	public Post getPostByPostId(Integer postId);
	/**
	 * 获取回复数
	 * @param postId
	 * @return
	 */
	Integer getReplyNum(Integer postId);
	/**
	 * 删除帖子
	 * @return
	 */
	public int deletePost(Integer postId);
	


}
