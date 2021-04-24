package com.habr.util;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ModelToDTO {

    private final static ModelMapper modelMapper = new ModelMapper();

    public static <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }

//    public static List<UserDTO> toUserDTO(List<User> users) {
//        List<UserDTO> articleDTOS = new ArrayList<>();
//        for (User user : users) {
//            articleDTOS.add(new UserDTO(user));
//        }
//        return articleDTOS;
//    }
//
//    public static List<ArticleDTO> toArticleDTO(List<Article> articles) {
//        List<ArticleDTO> articleDTOS = new ArrayList<>();
//        for (Article article : articles) {
//            articleDTOS.add(new ArticleDTO(article));
//        }
//        return articleDTOS;
//    }
}
