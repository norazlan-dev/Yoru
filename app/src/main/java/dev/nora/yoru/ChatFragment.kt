package dev.nora.yoru

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import dev.nora.yoru.databinding.FragmentChatBinding
import java.time.LocalDate
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
            "Hello, how are you? How's it going?",
            "What's up?",
            "Nice to meet you. How's it going?",
            "How's your day?",
            "Long time no see",
            "Good morning! How's it going?",
            "Good afternoon!",
            "Good evening!",
            "How's it going?",
            "Hello, how are you? What have you been up to?"
        )
        var from = ""

        binding.btnSend.setOnClickListener {
            val date = LocalDate.now()
            val dayOfWeekFormatter = DateTimeFormatter.ofPattern("EEEE")
            val formattedDayOfWeek = date.format(dayOfWeekFormatter)
            val dayOfMonth = date.dayOfMonth
            val monthFormatter = DateTimeFormatter.ofPattern("MMM")
            val formattedMonth = date.format(monthFormatter)
            val time = LocalTime.now()
            val timeFormatter = DateTimeFormatter.ofPattern("h:mm a")
            val formattedTime = time.format(timeFormatter)
            val formattedDateTime = "$formattedDayOfWeek, $dayOfMonth $formattedMonth : $formattedTime"

            if (binding.chatFrame.childCount > 0 && from == "send") {
                val lastMessageView = binding.chatFrame.getChildAt(binding.chatFrame.childCount - 1) as LinearLayout
                val lastMessageTextView = lastMessageView.findViewById<TextView>(R.id.message_time)
                if (lastMessageTextView.text.toString() == formattedDateTime) {
                    lastMessageTextView.visibility = View.GONE
                }
            }

            val messageView = LayoutInflater.from(context).inflate(R.layout.asset_chatbox, null) as LinearLayout
            messageView.findViewById<TextView>(R.id.message_text).text = "Hello, ${words.random()} ${words.random()}"
            messageView.findViewById<TextView>(R.id.message_time).text = "$formattedDateTime"
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.gravity = Gravity.END
            layoutParams.topMargin = context?.resources?.getDimensionPixelSize(R.dimen.margin_8dp)!!
            layoutParams.marginStart = context?.resources?.getDimensionPixelSize(R.dimen.margin_32dp)!!
            messageView.layoutParams = layoutParams
            binding.chatFrame.addView(messageView)
            from = "send"
            binding.chatFrameScroll.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    binding.chatFrameScroll.post {
                        binding.chatFrameScroll.fullScroll(View.FOCUS_DOWN)
                    }
                    binding.chatFrameScroll.viewTreeObserver.removeOnGlobalLayoutListener(this)
                }
            })
        }

        binding.btnReceive.setOnClickListener {
            val date = LocalDate.now()
            val dayOfWeekFormatter = DateTimeFormatter.ofPattern("EEEE")
            val formattedDayOfWeek = date.format(dayOfWeekFormatter)
            val dayOfMonth = date.dayOfMonth
            val monthFormatter = DateTimeFormatter.ofPattern("MMM")
            val formattedMonth = date.format(monthFormatter)
            val time = LocalTime.now()
            val timeFormatter = DateTimeFormatter.ofPattern("h:mm a")
            val formattedTime = time.format(timeFormatter)
            val formattedDateTime = "$formattedDayOfWeek, $dayOfMonth $formattedMonth : $formattedTime"

            if (binding.chatFrame.childCount > 0 && from == "receive") {
                val lastMessageView = binding.chatFrame.getChildAt(binding.chatFrame.childCount - 1) as LinearLayout
                val lastMessageTextView = lastMessageView.findViewById<TextView>(R.id.message_time)
                if (lastMessageTextView.text.toString() == formattedDateTime) {
                    lastMessageTextView.visibility = View.GONE
                }
            }

            val messageView = LayoutInflater.from(context).inflate(R.layout.asset_chatbox_alt, null) as LinearLayout
            messageView.findViewById<TextView>(R.id.message_text).text = "Hey, ${words.random()} ${words.random()}"
            messageView.findViewById<TextView>(R.id.message_time).text = "$formattedDateTime"
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            messageView.layoutParams = layoutParams
            layoutParams.topMargin = context?.resources?.getDimensionPixelSize(R.dimen.margin_8dp)!!
            layoutParams.marginEnd = context?.resources?.getDimensionPixelSize(R.dimen.margin_32dp)!!
            binding.chatFrame.addView(messageView)
            from = "receive"
            binding.chatFrameScroll.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    binding.chatFrameScroll.post {
                        binding.chatFrameScroll.fullScroll(View.FOCUS_DOWN)
                    }
                    binding.chatFrameScroll.viewTreeObserver.removeOnGlobalLayoutListener(this)
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}