package demo.mpp.Service.social;

import demo.mpp.Entity.social.CommentEntity;
import demo.mpp.Entity.social.LikeEntity;
import demo.mpp.Entity.social.PostEntity;
import demo.mpp.Repository.social.CommentRepository;
import demo.mpp.Repository.social.LikeRepository;
import demo.mpp.Repository.social.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private LikeRepository likeRepository;

    public List<PostEntity> getPostListWithFullDetail(Integer userId) {
        List<PostEntity> postEntityList = postRepository.findAllByUserIdEquals(userId);
        postEntityList.forEach(postEntity -> {
            // get comment
            postEntity.setCommentEntityList(commentRepository.findAllByPostIdEquals(postEntity.getPostId()));
            // get like
            postEntity.setLikeEntityList(likeRepository.findAllByPostIdEquals(postEntity.getPostId()));
        });
        return postEntityList;
    }

}
