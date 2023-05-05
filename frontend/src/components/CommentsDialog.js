import React, {useState} from "react";
import {Dialog, DialogContent, DialogContentText, DialogTitle, TextField} from "@mui/material";
import Button from "./Button";

export function CommentsDialog({dialogOpen, setDialogOpen}) {
    const [comment, setComment] = useState('');
    const [comments, setComments] = useState([]);

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
                            <div>комментарии</div>
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