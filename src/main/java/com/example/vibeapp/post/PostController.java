package com.example.vibeapp.post;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public String getPostList(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        int pageSize = 5;
        List<PostListDto> posts = postService.getPostsByPage(page, pageSize);
        int totalPages = postService.getTotalPages(pageSize);

        model.addAttribute("posts", posts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "post/posts";
    }

    @GetMapping("/posts/{no}")
    public String getPostDetail(@PathVariable("no") Long no, Model model) {
        PostResponseDto post = postService.getPostByNo(no);
        model.addAttribute("post", post);
        return "post/post_detail";
    }

    @GetMapping("/posts/new")
    public String getPostNewForm(Model model) {
        model.addAttribute("postCreateDto", new PostCreateDto("", "", ""));
        return "post/post_new_form";
    }

    @PostMapping("/posts/add")
    public String registerPost(@Valid @ModelAttribute PostCreateDto postCreateDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "post/post_new_form";
        }
        postService.addPost(postCreateDto);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{no}/edit")
    public String getPostEditForm(@PathVariable("no") Long no, Model model) {
        PostResponseDto post = postService.getPostByNo(no);
        model.addAttribute("postUpdateDto", new PostUpdateDto(post.title(), post.content(), post.tags()));
        model.addAttribute("no", no);
        return "post/post_edit_form";
    }

    @PostMapping("/posts/{no}/save")
    public String modifyPost(@PathVariable("no") Long no, @Valid @ModelAttribute PostUpdateDto postUpdateDto,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("no", no);
            return "post/post_edit_form";
        }
        postService.updatePost(no, postUpdateDto);
        return "redirect:/posts/" + no;
    }

    @GetMapping("/posts/{no}/delete")
    public String removePost(@PathVariable("no") Long no) {
        postService.deletePost(no);
        return "redirect:/posts";
    }
}
