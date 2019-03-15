package demo.mpp.Service;

import demo.mpp.Entity.PostEntity;
import demo.mpp.Repository.CommentRepository;
import demo.mpp.Repository.EmotionRepository;
import demo.mpp.Repository.PostRepository;
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
