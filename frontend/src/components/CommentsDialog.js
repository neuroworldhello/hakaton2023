import React from "react";
import {Dialog, DialogContent, DialogContentText, DialogTitle, TextField} from "@mui/material";

export function CommentsDialog({dialogOpen, setDialogOpen}) {
    const handleDialogClose = () => {
        setDialogOpen(false);
    }

    return (
        <Dialog
            open={dialogOpen}
            onClose={handleDialogClose}
            maxWidth="sm"
            fullWidth={true}
        >
            <DialogTitle>Комментарии</DialogTitle>
            <DialogContent className='pb-10'>
                <DialogContentText className='pt-6'>
                    <TextField
                        id="outlined-multiline-static"
                        label="Ваш комментарий"
                        multiline
                        rows={4}
                        defaultValue=''
                        className="w-full"
                    />
                </DialogContentText>
            </DialogContent>

        </Dialog>
    )
}