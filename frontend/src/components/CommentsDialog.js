import React, {useEffect, useState} from "react";
import {Dialog, DialogContent, DialogContentText, DialogTitle, TextField, Typography} from "@mui/material";
import Button from "./Button";
import axios from "axios";
import {toast} from "react-toastify";
import {toastOptions} from "./consts";

export function CommentsDialog({dialogOpen, setDialogOpen, practiceId}) {
    const [comment, setComment] = useState('');
    const [comments, setComments] = useState([]);

    useEffect(() => {
        if (dialogOpen && practiceId) {
            axios.get(`/api/practices/${practiceId}/comments`)
                .then(response => {
                    console.log(response.data)
                    setComments(response.data);
                })

                .catch(() => toast.error('Ошибка получения комментариев', toastOptions))
        }
    }, [practiceId, dialogOpen])

    const handleDialogClose = () => {
        setDialogOpen(false);
    }

    const handleSaveButtonClick = () => {
        axios.post(`/api/practices/${practiceId}/comments?text=${comment}`)
            .then(() => toast('Комментарий добавлен', toastOptions))
            .catch(() => toast.error('Ошибка добавления комментария', toastOptions))

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
                                    <div className='flex flex-row items-center'>
                                        <Typography variant="h6" className='pr-6'>{comment.author}</Typography>
                                        <Typography variant='body2' className='pt-1'>{comment.createdAt}</Typography>
                                    </div>
                                    <Typography>{comment.text}</Typography>
                                </div>)}
                        </div>
                        }
                        {comments.length === 0 &&
                        <Typography className='flex justify-center'>
                            Список комментариев к этой практике пуст. Будьте первым!
                        </Typography>
                        }
                    </div>
                </DialogContentText>
            </DialogContent>
        </Dialog>
    )
}