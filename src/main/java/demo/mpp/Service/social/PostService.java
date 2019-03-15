package demo.mpp.Service.social;

import demo.mpp.Entity.social.EmotionEntity;
import demo.mpp.Entity.social.PostEntity;
import demo.mpp.Repository.social.CommentRepository;
import demo.mpp.Repository.social.EmotionRepository;
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
    private EmotionRepository emotionRepository;

    public List<PostEntity> getPostListWithFullDetail(Integer userId) {
        List<PostEntity> postEntityList = postRepository.findAllByUserIdEquals(userId);
        postEntityList.forEach(postEntity -> {
            // get comment
            postEntity.setCommentEntityList(commentRepository.findAllByPostIdEquals(postEntity.getPostId()));
            // get emotion
            postEntity.setEmotionEntityList(emotionRepository.findAllByPostIdEquals(postEntity.getPostId()));
        });
        return postEntityList;
    }

}
