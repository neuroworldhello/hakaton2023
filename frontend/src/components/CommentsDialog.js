import React, {useState} from "react";
import {Dialog, DialogContent, DialogContentText, DialogTitle, TextField} from "@mui/material";
import Button from "./Button";

const testData = [
    {
        id: 1,
        text: 'Формирование описания экранов',
        practice: 'Формирование описания экранов',
        author: 'Автор 1',
        createdAt: '2022-05-03'
    },
    {
        id: 2,
        text: 'На основе OpenAPI Spec документа реализация Spring Boot приложения с использованием требуемых технологий',
        practice: '',
        author: 'Автор 2',
        createdAt: '2022-05-03'
    },
    {id: 3, text: 'Code review', practice: 'Формирование описания экранов', author: 'Автор 3', createdAt: '2022-05-03'},
    {
        id: 4,
        text: 'Составление тест-кейсов на основе описания технических решений',
        practice: 'Формирование описания экранов',
        author: 'Автор 4',
        createdAt: '2022-05-03'
    },
    {
        id: 5,
        text: 'Генерация Ansible-vars файла для конфигурации LDAP из конфигурации существующего LDAP-сервера.',
        practice: 'Формирование описания экранов',
        author: 'Автор 5',
        createdAt: '2022-05-03'
    },
]

export function CommentsDialog({dialogOpen, setDialogOpen}) {
    const [comment, setComment] = useState('');
    const [comments, setComments] = useState(testData);

    const handleDialogClose = () => {
        setDialogOpen(false);
    }

    const handleSaveButtonClick = () => {
        console.log('click!')
        setDialogOpen(false);
        setComment('');
    }

    const handleCommentChange = (event) => {
        setComment(event.target.value);
    }

    return (
        <Dialog
            open={dialogOpen}
            onClose={handleDialogClose}
            maxWidth="sm"
            fullWidth={true}
        >
            <DialogTitle>Комментарии</DialogTitle>
            <DialogContent className='py-10'>
                <DialogContentText className='pt-6 pb-10'>
                    <TextField
                        id="outlined-multiline-static"
                        label="Ваш комментарий"
                        multiline
                        rows={4}
                        value={comment}
                        onChange={handleCommentChange}
                        className="w-full"
                    />
                    <div className='py-10 flex justify-end'>
                        <Button
                            onClick={handleSaveButtonClick}
                            disabled={!comment}
                        >
                            Добавить
                        </Button>
                    </div>
                    <div>
                        {comments.length > 0 &&
                        <div>
                            {comments.map(comment =>
                                <div id={comment.id} className='py-10'>
                                    <div className='flex flex-row'>
                                        <div className='pr-6'>{comment.author}</div>
                                        <div>{comment.createdAt}</div>
                                    </div>
                                    {comment.text}
                                </div>)}
                        </div>
                        }
                        {comments.length === 0 &&
                        <div className='flex justify-center'>
                            Список комментариев к этой практике пуст. Будьте первым!
                        </div>
                        }
                    </div>
                </DialogContentText>
            </DialogContent>
        </Dialog>
    )
}