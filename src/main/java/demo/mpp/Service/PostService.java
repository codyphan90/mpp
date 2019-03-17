package demo.mpp.Service;

import demo.mpp.Entity.CommentEntity;
import demo.mpp.Entity.EmotionEntity;
import demo.mpp.Entity.FriendShipEntity;
import demo.mpp.Entity.PostEntity;
import demo.mpp.Repository.CommentRepository;
import demo.mpp.Repository.EmotionRepository;
import demo.mpp.Repository.FriendShipRepository;
import demo.mpp.Repository.PostRepository;
import demo.mpp.utils.LambdaLibrary;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private EmotionRepository emotionRepository;

    @Autowired
    private FriendShipRepository friendShipRepository;

    public List<PostEntity> getPostListWithFullDetail(String userName) {
        List<PostEntity> postEntityList = postRepository.findAllByUserNameEquals(userName);
        getCommentAndReaction(postEntityList);
        postEntityList.sort(Comparator.comparing(PostEntity::getCreatedDate).reversed());
        return postEntityList;
    }

    public List<PostEntity> getNewFeed(String userName) {
        List<PostEntity> allPost = postRepository.findAll();
        List<FriendShipEntity> allFriendShip = friendShipRepository.findAllByUserNameEquals(userName);

        // get follow users then get posts of them
        List<PostEntity> newFeed = LambdaLibrary.NEW_FEED.apply(allPost, LambdaLibrary.GET_FOLLOW_USER.apply(allFriendShip));

        getCommentAndReaction(newFeed);
        return newFeed;

    }

    public PostEntity createPost(PostEntity postEntity) {
        return  postRepository.save(postEntity);
    }

    public CommentEntity createComment(CommentEntity commentEntity) {
        return commentRepository.save(commentEntity);
    }

    public EmotionEntity createEmotion(EmotionEntity emotionEntity) {
        return emotionRepository.save(emotionEntity);
    }

    private void getCommentAndReaction(List<PostEntity> postEntityList) {
        postEntityList.forEach(postEntity -> {
            // get comment
            List<CommentEntity> commentEntityList = commentRepository.findAllByPostIdEquals(postEntity.getPostId());
            commentEntityList.sort(Comparator.comparing(CommentEntity::getCreatedDate));
            postEntity.setCommentEntityList(commentEntityList);

            // get emotion
            postEntity.setEmotionEntityList(emotionRepository.findAllByPostIdEquals(postEntity.getPostId()));
        });
    }

}
