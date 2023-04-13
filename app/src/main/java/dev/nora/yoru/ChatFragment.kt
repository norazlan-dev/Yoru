package dev.nora.yoru

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import dev.nora.yoru.databinding.FragmentChatBinding
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class ChatFragment : Fragment() {

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val words = listOf(
            "APPLE", "BANANA", "CHERRY", "DATE", "ELDERBERRY", "FIG",
            "GRAPE", "HONEYDEW", "KIWI", "LEMON", "MANGO", "NECTARINE",
            "ORANGE", "PEACH", "QUINCE", "RASPBERRY", "STRAWBERRY", "TANGERINE",
            "UGLI FRUIT", "WATERMELON"
        )
        var x = 0

        binding.btnSend.setOnClickListener {
            val time = LocalTime.now()
            val formatter = DateTimeFormatter.ofPattern("h:mm:ss a")
            val formattedTime = time.format(formatter)

            if (x % 2 == 0) {
                val messageView = TextView(context)
                messageView.text = "Hello, ${words.random()}!"
                messageView.gravity = Gravity.END
                val messageViewTime = TextView(context)
                messageViewTime.text = "Sent $formattedTime"
                messageViewTime.gravity = Gravity.END
                binding.chatFrame.addView(messageView)
                binding.chatFrame.addView(messageViewTime)
                binding.chatFrameScroll.fullScroll(View.FOCUS_DOWN)
            }
            else {
                val messageView = TextView(context)
                messageView.text = "Hello,  ${words.random()}!"
                val messageViewTime = TextView(context)
                messageViewTime.text = "Sent $formattedTime"
                binding.chatFrame.addView(messageView)
                binding.chatFrame.addView(messageViewTime)
                binding.chatFrameScroll.fullScroll(View.FOCUS_DOWN)
            }
            x++
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}